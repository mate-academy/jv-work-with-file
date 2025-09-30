package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final String supplyKey = "supply";
    private static final String buyKey = "buy";
    private static final String inputDelimiter = ",";
    private static final String outputDelimiter = ",";
    private static final String newline = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file " + fromFileName);
        }

        long totalSupply = 0;
        long totalBuy = 0;

        for (String line : lines) {
            String trimmedLine = line.toLowerCase().trim();
            if (trimmedLine.isEmpty()) {
                continue;
            }
            if (trimmedLine.contains(inputDelimiter)) {
                String[] parts = trimmedLine.split(inputDelimiter);
                if (parts.length != 2 || parts[1].trim().isEmpty()) {
                    continue;
                }
                String type = parts[0].trim();
                String amountString = parts[1].trim();
                try {
                    int amount = Integer.parseInt(amountString);
                    if (type.equals(supplyKey)) {
                        totalSupply += amount;
                    } else if (type.equals(buyKey)) {
                        totalBuy += amount;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
            long totalResult = totalSupply - totalBuy;
            String finalReport = supplyKey + outputDelimiter + totalSupply + newline
                    + buyKey + outputDelimiter + totalBuy + newline
                    + "result" + outputDelimiter + totalResult;
            try {
                Files.writeString(Path.of(toFileName), finalReport);
            } catch (IOException e) {
                throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }
}
