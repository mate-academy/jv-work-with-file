package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_PATTERN = "supply";
    private static final String BUY_PATTERN = "buy";
    private static final String RESULT_PATTERN = "result";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileDataString = readFile(fromFileName);
        String resultData = countOperationsValue(fileDataString);
        writeFile(toFileName, resultData);
    }

    private String countOperationsValue(String data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        String[] fileData = data.split(System.lineSeparator());
        for (String line : fileData) {
            String[] split = line.split(",");
            if (split[INDEX_OF_OPERATION].equals(SUPPLY_PATTERN)) {
                supplyCounter += Integer.parseInt(split[INDEX_OF_VALUE]);
            } else {
                buyCounter += Integer.parseInt(split[INDEX_OF_VALUE]);
            }
        }
        int result = supplyCounter - buyCounter;
        return SUPPLY_PATTERN + "," + supplyCounter + System.lineSeparator()
                + BUY_PATTERN + "," + buyCounter + System.lineSeparator()
                + RESULT_PATTERN + "," + result;
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private void writeFile(String toFile, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(toFile)))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Could not write file " + toFile, e);
        }
    }
}
