package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String NEW_LINE = System.lineSeparator();
    private static final String VARIABLE1 = "supply";
    private static final String VARIABLE2 = "buy";
    private static final String RESULT_VARIABLES = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String result = calculateDate(data);
        writeDateToFile(toFileName, result);
    }

    private String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(NEW_LINE);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return builder.toString();
    }

    private String calculateDate(String data) {
        StringBuilder builder = new StringBuilder();
        String[] line = data.split(NEW_LINE);
        int supplySum = 0;
        int buySum = 0;
        for (String lines : line) {
            String[] parts = lines.split(",");
            if (parts[OPERATION_TYPE_INDEX].equals(VARIABLE1)) {
                supplySum += Integer.parseInt(parts[AMOUNT_INDEX]);
            } else if (parts[OPERATION_TYPE_INDEX].equals(VARIABLE2)) {
                buySum += Integer.parseInt(parts[AMOUNT_INDEX]);
            }
        }
        builder.append(VARIABLE1).append(",").append(supplySum)
                .append(NEW_LINE)
                .append(VARIABLE2).append(",").append(buySum)
                .append(NEW_LINE)
                .append(RESULT_VARIABLES)
                .append(",").append(supplySum - buySum);
        return builder.toString();
    }

    private void writeDateToFile(String toFile, String result) {
        File newFile = new File(toFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
