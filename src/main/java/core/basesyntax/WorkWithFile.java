package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readAndCalculateTotals(fromFileName);
        String report = generateReport(totals[0], totals[1]);
        writeReportToFile(toFileName, report);
    }

    /**
     * Reads CSV file and calculates total supply and buy values.
     *
     * @param fileName the name of the CSV file
     * @return int array where index 0 = total supply, index 1 = total buy
     */
    private int[] readAndCalculateTotals(String fileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(SEPARATOR);
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals(SUPPLY)) {
                    supplySum += amount;
                } else if (operation.equals(BUY)) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    /**
     * Creates formatted report string.
     *
     * @param supplySum total supply
     * @param buySum    total buy
     * @return formatted report text
     */
    private String generateReport(int supplySum, int buySum) {
        int result = supplySum - buySum;
        return SUPPLY + SEPARATOR + supplySum + "\n"
                + BUY + SEPARATOR + buySum + "\n"
                + RESULT + SEPARATOR + result;
    }

    /**
     * Writes the generated report string to file.
     *
     * @param fileName name of the output file
     * @param report   text to write
     */
    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}

