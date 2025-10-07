package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_DELIMITER = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String REPORT_SUPPLY = "supply,";
    private static final String REPORT_BUY = "buy,";
    private static final String REPORT_RESULT = "result,";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        Totals totals = readAndAggregate(fromFileName);
        String report = buildReport(totals.supply, totals.buy);
        writeReport(toFileName, report);
    }

    private Totals readAndAggregate(String fromFileName) {
        int totalBuy = 0;
        int totalSupply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(CSV_DELIMITER);
                String operation = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());

                if (operation.equals(OPERATION_BUY)) {
                    totalBuy += value;
                } else if (operation.equals(OPERATION_SUPPLY)) {
                    totalSupply += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }

        return new Totals(totalSupply, totalBuy);
    }

    private String buildReport(int totalSupply, int totalBuy) {
        int result = totalSupply - totalBuy;
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(REPORT_SUPPLY).append(totalSupply).append(LINE_SEPARATOR)
                .append(REPORT_BUY).append(totalBuy).append(LINE_SEPARATOR)
                .append(REPORT_RESULT).append(result);
        return reportBuilder.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private static class Totals {
        private final int supply;
        private final int buy;

        public Totals(int supply, int buy) {
            this.supply = supply;
            this.buy = buy;
        }
    }
}
