package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ZERO_INITIALIZE = 0;
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_LITERAL = "supply";
    private static final String BUY_LITERAL = "buy";
    private static final String RESULT_LITERAL = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = processStatistics(data);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from " + fromFileName, e);
        }
        return data.toString().split(System.lineSeparator());
    }

    private String processStatistics(String[] data) {
        int totalSupply = ZERO_INITIALIZE;
        int totalBuy = ZERO_INITIALIZE;

        for (String line : data) {
            String[] parts = line.split(COMMA);
            String operationType = parts[OPERATION_INDEX];
            int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (operationType.equals(SUPPLY_LITERAL)) {
                totalSupply += amount;
            } else if (operationType.equals(BUY_LITERAL)) {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_LITERAL).append(COMMA).append(totalSupply)
                .append(System.lineSeparator());
        report.append(BUY_LITERAL).append(COMMA).append(totalBuy)
                .append(System.lineSeparator());
        report.append(RESULT_LITERAL).append(COMMA).append(result)
                .append(System.lineSeparator());

        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file " + toFileName);
        }
    }
}
