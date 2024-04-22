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
            String[] data = readFile(fromFileName);
            String report = createReport(data);
            writeToFile(report, toFileName);
            System.out.println("Statistics generated and written to " + toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read or write file", e);
        }
    }

    private String[] readFile(String fromFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            return reader.lines().toArray(String[]::new);
        }
    }

    private String createReport(String[] data) {
        int totalSupply = 0;
        int totalBuy = 0;
        StringBuilder reportBuilder = new StringBuilder();

        for (String line : data) {
            String[] parts = line.split(COMMA);
            if (parts.length == 2) {
                int amount = Integer.parseInt(parts[1].trim());
                if (parts[0].equals(SUPPLY)) {
                    totalSupply += amount;
                } else if (parts[0].equals(BUY)) {
                    totalBuy += amount;
                }
            }
        }

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
        }
    }
}
