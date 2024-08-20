package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> reportData = readFile(fromFileName);
        writeReport(toFileName, reportData);
    }

    private Map<String, Integer> readFile(String fromFileName) {
        Map<String, Integer> reportData = new HashMap<>();
        reportData.put(SUPPLY, 0);
        reportData.put(BUY, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                String operation = parts[OPERATION_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                reportData.put(operation, reportData.get(operation) + amount);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        return reportData;
    }

    private void writeReport(String toFileName, Map<String, Integer> reportData) {
        int totalSupply = reportData.get(SUPPLY);
        int totalBuy = reportData.get(BUY);
        int result = totalSupply - totalBuy;

        StringBuilder reportBuilder = new StringBuilder();
        appendFormattedLine(reportBuilder, SUPPLY, totalSupply);
        appendFormattedLine(reportBuilder, BUY, totalBuy);
        appendFormattedLine(reportBuilder, RESULT, result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }

    private void appendFormattedLine(StringBuilder builder, String operation, int amount) {
        builder.append(operation)
                .append(COMMA)
                .append(amount)
                .append(System.lineSeparator());
    }
}

