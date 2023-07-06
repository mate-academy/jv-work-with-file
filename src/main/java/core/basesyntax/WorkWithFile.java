package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int RECORD_NAME_INDEX = 0;
    private static final int RECORD_VALUE_INDEX = 1;

    public static void getStatistic(String fromFileName, String toFileName) {
        deleteResultFileIfExist(toFileName);
        List<String> records = readCsvToList(fromFileName);
        String[] report = generateReport(records);
        writeToFile(report, new File(toFileName));
    }

    private static String[] generateReport(List<String> records) {
        int[] totalSupplyBuyResult = new int[3];
        calculateSupplyAndBuy(totalSupplyBuyResult, records);
        totalSupplyBuyResult[2] = totalSupplyBuyResult[SUPPLY_INDEX] - totalSupplyBuyResult[BUY_INDEX];
        String[] resultData = new String[3];
        resultData[0] = "supply," + totalSupplyBuyResult[SUPPLY_INDEX];
        resultData[1] = "buy," + totalSupplyBuyResult[BUY_INDEX];
        resultData[2] = "result," + totalSupplyBuyResult[RESULT_INDEX];
        return resultData;
    }

    private static void deleteResultFileIfExist(String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can not delete if exist file " + e);
        }
    }

    private static void calculateSupplyAndBuy(int[] resultArray, List<String> records) {
        for (int i = 0; i < records.size(); i++) {
            String[] record = records.get(i).split(",");
            if (record[RECORD_NAME_INDEX].equals("supply")) {
                resultArray[0] += Integer.parseInt(record[RECORD_VALUE_INDEX]);
            } else if (record[RECORD_NAME_INDEX].equals("buy")) {
                resultArray[1] += Integer.parseInt(record[RECORD_VALUE_INDEX]);
            } else {
                throw new RuntimeException("Invalid record type " + records.get(0));
            }
        }
    }

    private static void writeToFile(String[] result, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can not create file" + e);
        }
        for (int i = 0; i < result.length; i++) {
            try {
                Files.write(file.toPath(), (result[i]
                                + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Error writing file" + e);
            }
        }
    }

    private static List<String> readCsvToList(String fileName) {
        List<String> records = null;
        try {
            records = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can not read file " + fileName + e);
        }
        return records;
    }
}
