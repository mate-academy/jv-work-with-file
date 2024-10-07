package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {

        int[] totals = calculateTotals(fromFileName);

        int totalSupply = totals[ZERO];

        int totalBuy = totals[ONE];

        int totalAmount = totalSupply - totalBuy;

        processReport(toFileName, totalSupply, totalBuy, totalAmount);

    }

    private int[] calculateTotals(String fromFileName) {
        try (BufferedReader reader = createReader(fromFileName)) {
            return calculateTotalsFromReader(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }
    }

    private BufferedReader createReader(String fromFileName) throws IOException {
        return new BufferedReader(new FileReader(fromFileName));
    }

    private int[] calculateTotalsFromReader(BufferedReader reader) throws IOException {
        int totalSupply = ZERO;
        int totalBuy = ZERO;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(COMMA);
            String operationType = data[ZERO];
            int amount = Integer.parseInt(data[ONE]);

            if (operationType.equals(SUPPLY)) {
                totalSupply += amount;
            } else if (operationType.equals(BUY)) {
                totalBuy += amount;
            }
        }

        return new int[] {totalSupply, totalBuy};
    }

    private void processReport(String toFileName, int totalSupply, int totalBuy, int totalAmount) {
        String report = prepareReport(totalSupply, totalBuy, totalAmount);
        writeReport(toFileName, report);
    }

    private String prepareReport(int totalSupply, int totalBuy, int totalAmount) {
        StringBuilder report = new StringBuilder();
        report.append(formatReportLine(SUPPLY, totalSupply))
                .append(formatReportLine(BUY, totalBuy))
                .append(formatReportLine(RESULT, totalAmount));
        return report.toString();
    }

    private String formatReportLine(String label, int value) {
        return label + COMMA + value + System.lineSeparator();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + toFileName, e);
        }
    }
}

