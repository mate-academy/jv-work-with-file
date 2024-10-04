package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);

        String resultData = calculateStatistics(data);

        writeData(toFileName, resultData);
    }

    private String readData(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file: " + fileName, e);
        }
    }

    private String calculateStatistics(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        int result;

        for (String line : data.split(LINE_SEPARATOR)) {
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split(COMMA);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid line format: " + line);
            }

            String operation = parts[OPERATION_INDEX].trim();
            int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());

            if (operation.equals(SUPPLY)) {
                totalSupply += amount;
            } else if (operation.equals(BUY)) {
                totalBuy += amount;
            }
        }

        result = totalSupply - totalBuy;

        StringBuilder resultData = new StringBuilder();
        resultData.append(SUPPLY)
                .append(COMMA)
                .append(totalSupply)
                .append(LINE_SEPARATOR)
                .append(BUY)
                .append(COMMA)
                .append(totalBuy)
                .append(LINE_SEPARATOR)
                .append(RESULT)
                .append(COMMA)
                .append(result);

        return resultData.toString();
    }

    private void writeData(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file: " + fileName, e);
        }
    }
}
