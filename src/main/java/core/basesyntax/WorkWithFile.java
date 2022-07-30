package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class WorkWithFile {
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int SUPPLY_VALUE_INDEX = 0;
    private static final int BUY_VALUE_INDEX = 1;
    private static final String BUY_NAME = "buy";
    private static final String SUPPLY_NAME = "supply";
    private static final String RESULT_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] readInfo = readFromFile(fromFileName);
        String result = calculateResult(readInfo);
        writeToFile(result, toFileName);
    }

    private int[] readFromFile(String fileName) {
        int buyValue = 0;
        int supplyValue = 0;
        int[] values = new int[BUY_VALUE_INDEX + 1];
        String[] lineValueArray;
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String lineValue = bufferedReader.readLine();
            while (lineValue != null) {
                lineValueArray = lineValue.split(",");
                if (lineValueArray[OPERATION_NAME_INDEX].equals(BUY_NAME)) {
                    buyValue += Integer.parseInt(lineValueArray[VALUE_INDEX]);
                } else {
                    supplyValue += Integer.parseInt(lineValueArray[VALUE_INDEX]);
                }
                lineValue = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find the file", e);
        } catch (IOException ex) {
            throw new RuntimeException("Can't read the file", ex);
        }
        values[SUPPLY_VALUE_INDEX] = supplyValue;
        values[BUY_VALUE_INDEX] = buyValue;
        System.out.println(Arrays.toString(values));
        return values;
    }

    private String calculateResult(int[] inputValues) {
        int resultValue = inputValues[SUPPLY_VALUE_INDEX] - inputValues[BUY_VALUE_INDEX];
        StringBuilder result = new StringBuilder(SUPPLY_NAME);
        result = result.append(",").append(inputValues[SUPPLY_VALUE_INDEX])
                .append(System.lineSeparator()).append(BUY_NAME).append(",")
                .append(inputValues[BUY_VALUE_INDEX]).append(System.lineSeparator())
                .append(RESULT_NAME).append(",").append(resultValue);
        System.out.println(result);
        return String.valueOf(result);
    }

    public void writeToFile(String result, String toFileName) {
        Path filePath = Paths.get(toFileName);
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Can't create a file", e);
            }
        }
        try {
            Files.write(filePath, result.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to filePath", e);
        }
    }
}
