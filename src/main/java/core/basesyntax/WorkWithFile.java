package core.basesyntax;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            Files.deleteIfExists(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can not delete if exist file " + e);
        }
        List<List<String>> records = readCsvToList(fromFileName);
        int[] totalSupplyBuyResult = new int[3];// index 0 - supply, 1 - buy, 2 - result
        calculateSupplyAndBuy(totalSupplyBuyResult, records);
        totalSupplyBuyResult[2] = totalSupplyBuyResult[0] - totalSupplyBuyResult[1];
        String[] resultData = new String[3];
        resultData[0] = "supply," + totalSupplyBuyResult[0];
        resultData[1] = "buy," + totalSupplyBuyResult[1];
        resultData[2] = "result," + totalSupplyBuyResult[2];
        writeToFile(resultData, new File(toFileName));
        System.out.println();

    }

    private void calculateSupplyAndBuy(int[] resultArray, List<List<String>> records) {
        for (int i = 0; i < records.size(); i++) {
            List<String> record = records.get(i);
            if (record.get(0).equals("supply")) {
                resultArray[0] += Integer.parseInt(record.get(1));
            } else if (record.get(0).equals("buy")) {
                resultArray[1] += Integer.parseInt(record.get(1));
            } else {
                throw new RuntimeException("Invalid record type " + record.get(0));
            }
        }
    }

    private void writeToFile(String[] result, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can not create file" + e);
        }
        for (int i = 0; i < result.length; i++) {
            try {
                Files.write(file.toPath(), (result[i] +
                        System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException("Error writing file" + e);
            }
        }
    }

    private List<List<String>> readCsvToList(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No file found ", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file ", e);
        }
        return records;
    }
}
