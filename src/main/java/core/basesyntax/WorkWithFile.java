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
    private int supplyCounter = 0;
    private int buyCounter = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileDataString = readFile(fromFileName);
        countOperationsValue(fileDataString);
        int result = supplyCounter - buyCounter;
        writeFile(toFileName, new String[]{SUPPLY_PATTERN + "," + supplyCounter,
                BUY_PATTERN + "," + buyCounter, RESULT_PATTERN + "," + result});
    }

    private void countOperationsValue(String fileDataString) {
        String[] fileData = fileDataString.split(System.lineSeparator());
        for (String line : fileData) {
            String[] split = line.split(",");
            if (split[INDEX_OF_OPERATION].equals(SUPPLY_PATTERN)) {
                supplyCounter += Integer.parseInt(split[INDEX_OF_VALUE]);
            } else {
                buyCounter += Integer.parseInt(split[INDEX_OF_VALUE]);
            }
        }
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
            throw new RuntimeException("Could not read file " + fileName);
        }
        return stringBuilder.toString();
    }

    private void writeFile(String toFile, String[] lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(toFile)))) {
            for (String line : lines) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not write file " + toFile);
        }

    }
}
