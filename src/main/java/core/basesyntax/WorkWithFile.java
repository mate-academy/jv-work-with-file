package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DEFAULT_SUM = 0;
    private static final int FIRST_CELL_ARRAY = 0;
    private static final int SECOND_CELL_ARRAY = 1;
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = calculateReport(data);
        writeIntoFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private String calculateReport(String data) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = data.split(System.lineSeparator());
        int supplySum = DEFAULT_SUM;
        int buySum = DEFAULT_SUM;
        for (String line : lines) {
            String[] fields = line.split(COMMA);
            if (fields.length == 2) {
                String operation = fields[FIRST_CELL_ARRAY].trim();
                int amount = Integer.parseInt(fields[SECOND_CELL_ARRAY].trim());
                if (operation.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operation.equals(BUY)) {
                    buySum += amount;
                }
            }
        }
        int result = supplySum - buySum;
        stringBuilder.append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return stringBuilder.toString();
    }

    private void writeIntoFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + toFileName, e);
        }
    }
}
