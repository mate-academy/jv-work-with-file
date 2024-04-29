package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            int[] data = readFile(fromFileName);
            int totalSupply = 0;
            int totalBuy = 0;
            for (int amount : data) {
                if (amount > 0) {
                    totalSupply += amount;
                } else {
                    totalBuy -= amount;
                }
            }
            String report = createReport(totalSupply, totalBuy);
            writeToFile(report, toFileName);
            System.out.println("Statistics generated and written to " + toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Statistics can't be generated", e);
        }
    }

    private int[] readFile(String fromFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines()
                    .mapToInt(line -> {
                        String[] parts = line.split(COMMA);
                        if (parts.length == 2) {
                            int amount = Integer.parseInt(parts[1].trim());
                            if (parts[0].equals(SUPPLY)) {
                                return amount;
                            } else if (parts[0].equals(BUY)) {
                                return -amount; // Negative amount for buy
                            }
                        }
                        return 0; // Return 0 for invalid lines
                    })
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException("File can't be opened", e);
        }
    }

    private String createReport(int totalSupply, int totalBuy) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(COMMA).append(totalSupply)
                .append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(totalBuy)
                .append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(totalSupply - totalBuy)
                .append(LINE_SEPARATOR);
        return reportBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written", e);
        }
    }
}
