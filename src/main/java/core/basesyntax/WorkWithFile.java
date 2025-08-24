package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    // Constants for operation types and file format
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String CSV_DELIMITER = ",";

    /**
     * A simple data carrier class to hold the calculated totals.
     * This is more readable and type-safe than using an array.
     */
    private static class Totals {
        private int supply;
        private int buy;
    }

    /**
     * Reads data from a source file, generates a statistic report, writes it
     * to a destination file, and returns the report as a String.
     *
     * @param fromFileName The path to the input CSV file.
     * @param toFileName   The path to the output report file.
     * @return The generated report as a String.
     */
    public String getStatistic(String fromFileName, String toFileName) {
        Totals totals = readAndCalculateTotals(fromFileName);
        String report = createReport(totals);
        writeToFile(toFileName, report);
        return report;
    }

    /**
     * Reads the source file, validates data, parses it, and calculates the total
     * for "supply" and "buy" operations. Malformed lines are ignored.
     *
     * @param fromFileName The path to the source data file.
     * @return A Totals object containing the sum for supply and buy.
     */
    private Totals readAndCalculateTotals(String fromFileName) {
        Totals totals = new Totals();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(CSV_DELIMITER);

                // Skip lines that are not in "key,value" format
                if (parts.length != 2) {
                    continue;
                }

                String operationType = parts[0];
                int amount;
                try {
                    amount = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    // Skip lines where the amount is not a valid number
                    continue;
                }

                if (SUPPLY_OPERATION.equals(operationType)) {
                    totals.supply += amount;
                } else if (BUY_OPERATION.equals(operationType)) {
                    totals.buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return totals;
    }

    /**
     * Creates a formatted report string from the calculated totals without a
     * trailing newline.
     *
     * @param totals The Totals object with supply and buy amounts.
     * @return A formatted multi-line string representing the report.
     */
    private String createReport(Totals totals) {
        int result = totals.supply - totals.buy;
        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder.append(SUPPLY_OPERATION).append(CSV_DELIMITER).append(totals.supply)
                .append(System.lineSeparator());
        reportBuilder.append(BUY_OPERATION).append(CSV_DELIMITER).append(totals.buy)
                .append(System.lineSeparator());
        reportBuilder.append(RESULT_OPERATION).append(CSV_DELIMITER).append(result);

        return reportBuilder.toString();
    }

    /**
     * Writes the given content to the specified file.
     *
     * @param toFileName    The path of the file to write to.
     * @param reportContent The string content to be written.
     */
    private void writeToFile(String toFileName, String reportContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportContent);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
