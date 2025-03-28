package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] report = createReport(fromFileName);
        writeToFile(toFileName, report);
    }

    public int[] createReport(String fromFileName) {
        int sumSupply = 0;
        int sumBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(DELIMITER);
                int amount = Integer.parseInt(data[1]);

                if (SUPPLY.equals(data[0])) {
                    sumSupply += amount;
                } else if (BUY.equals(data[0])) {
                    sumBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        return new int[]{sumSupply, sumBuy, sumSupply - sumBuy};
    }

    public void writeToFile(String toFileName, int[] report) {
        StringBuilder reportString = new StringBuilder()
                .append(SUPPLY).append(DELIMITER).append(report[0]).append(System.lineSeparator())
                .append(BUY).append(DELIMITER).append(report[1]).append(System.lineSeparator())
                .append("result").append(DELIMITER).append(report[2]);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(reportString.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}

