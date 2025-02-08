package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readData(fromFileName);
        int totalSupply = statistics[0];
        int totalBuy = statistics[1];
        int result = totalSupply - totalBuy;
        writeReport(toFileName, totalSupply, totalBuy, result);
    }

    private int[] readData(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new RuntimeException("Invalid line format: " + line);
                }

                String operation = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Error parsing the amount in line: " + line, e);
                }

                if (SUPPLY.equalsIgnoreCase(operation)) {
                    totalSupply += amount;
                } else if (BUY.equalsIgnoreCase(operation)) {
                    totalBuy += amount;
                } else {
                    throw new RuntimeException("Unknown operation in line: " + line);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading the file: " + fromFileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }

    private void writeReport(String toFileName, int totalSupply, int totalBuy, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(buildReport(totalSupply, totalBuy, result));
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file: " + toFileName, e);
        }

    }

    private String buildReport(int totalSupply, int totalBuy, int result) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(totalSupply).append(System.lineSeparator());
        report.append(BUY).append(",").append(totalBuy).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());
        return report.toString();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java WorkWithFile <inputFileName> <outputFileName>");
            return;
        }

        WorkWithFile statistic = new WorkWithFile();
        statistic.getStatistic(args[0], args[1]);
    }

}
