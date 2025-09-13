package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] transactions = readTransactions(fromFileName);
        int[] totals = calculateTotals(transactions);
        String report = buildReport(totals[0], totals[1]);
        writeReport(toFileName, report);
    }

    private String[] readTransactions(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
    }

    private int[] calculateTotals(String[] transactions) {
        int supply = 0;
        int buy = 0;
        for (String line : transactions) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                throw new RuntimeException("Invalid CSV format in line: \"" + line + "\"");
            }
            if (parts[0].equals(OPERATION_SUPPLY)) {
                supply += parseAmount(line, parts[1]);
            } else if (parts[0].equals(OPERATION_BUY)) {
                buy += parseAmount(line, parts[1]);
            }

        }
        return new int[]{supply, buy};
    }

    private String buildReport(int supply, int buy) {
        return OPERATION_SUPPLY + COMMA + supply + LINE_SEPARATOR
                + OPERATION_BUY + COMMA + buy + LINE_SEPARATOR
                + "result" + COMMA + (supply - buy);

    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private int parseAmount(String line, String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format in line: \"" + line + "\"", e);
        }
    }

}

