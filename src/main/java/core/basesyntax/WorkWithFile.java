package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int RECORD_NAME_INDEX = 0;
    private static final int RECORD_VALUE_INDEX = 1;
    private static final String SEPARATOR = ",";

    public static void getStatistic(String fromFileName, String toFileName) {
        List<String> records = readCsvToList(fromFileName);
        String report = generateReport(records);
        writeToFile(report, new File(toFileName));
    }

    private static String generateReport(List<String> records) {
        int[] totalSupplyBuyResult = calculateSupplyAndBuy(records);
        totalSupplyBuyResult[RESULT_INDEX] = totalSupplyBuyResult[SUPPLY_INDEX]
                - totalSupplyBuyResult[BUY_INDEX];

        return new StringBuilder().append("supply,").append(totalSupplyBuyResult[SUPPLY_INDEX])
                .append(System.lineSeparator())
                .append("buy,").append(totalSupplyBuyResult[BUY_INDEX])
                .append(System.lineSeparator())
                .append("result,").append(totalSupplyBuyResult[RESULT_INDEX])
                .append(System.lineSeparator()).toString();
    }

    private static int[] calculateSupplyAndBuy(List<String> records) {
        int[] resultArray = new int[3];
        for (int i = 0; i < records.size(); i++) {
            String[] record = records.get(i).split(SEPARATOR);
            if (record[RECORD_NAME_INDEX].equals("supply")) {
                resultArray[SUPPLY_INDEX] += Integer.parseInt(record[RECORD_VALUE_INDEX]);
            } else if (record[RECORD_NAME_INDEX].equals("buy")) {
                resultArray[BUY_INDEX] += Integer.parseInt(record[RECORD_VALUE_INDEX]);
            } else {
                throw new RuntimeException("Invalid record type " + records.get(0));
            }
        }
        return resultArray;
    }

    private static void writeToFile(String result, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to a file: " + file, e);
        }
    }

    private static List<String> readCsvToList(String fileName) {
        List<String> records = null;
        try {
            records = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can not read from the file: " + fileName, e);
        }
        return records;
    }
}
