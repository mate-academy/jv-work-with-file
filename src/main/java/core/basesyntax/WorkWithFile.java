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
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

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
                String[] parts = line.split(COMMA);
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }

                String operation = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Error parsing the amount in line: " + line, e);
                }

                switch (operation.toLowerCase()) {
                    case SUPPLY:
                        totalSupply += amount;
                        break;
                    case BUY:
                        totalBuy += amount;
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operation in line: " + line);
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
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(totalSupply).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(totalBuy).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(result).append(LINE_SEPARATOR)
                .toString();
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
