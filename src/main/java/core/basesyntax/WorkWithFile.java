package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String COMMA = ",";
    private static final int ZERO_INDEX = 0;
    private static final int INDEX_NUM_ONE = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeDateToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(NEW_LINE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        StringBuilder builder = new StringBuilder();
        String[] lines = data.split(NEW_LINE);
        int supplySum = 0;
        int buySum = 0;
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts[ZERO_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(parts[INDEX_NUM_ONE]);
            } else if (parts[ZERO_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(parts[INDEX_NUM_ONE]);
            }
        }
        builder.append(SUPPLY).append(COMMA).append(supplySum)
                .append(NEW_LINE)
                .append(BUY).append(COMMA).append(buySum)
                .append(NEW_LINE)
                .append(RESULT)
                .append(COMMA).append(supplySum - buySum);
        return builder.toString();
    }

    private void writeDateToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
