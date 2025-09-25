package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SPLIT_REGEX = "\\r?\\n";
    private static final String SEP = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String NEW_LINE = System.lineSeparator();

    private static final class Totals {
        private final int supply;
        private final int buy;

        Totals(int supply, int buy) {
            this.supply = supply;
            this.buy = buy;
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {

        String content = readFile(fromFileName);
        Totals totals = parseAndAggregate(content);
        String report = buildReport(totals);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try {
            return Files.readString(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from a file: " + fromFileName + e);
        }
    }

    private Totals parseAndAggregate(String content) {
        int sumSupply = 0;
        int sumBuy = 0;

        String[] lines = content.split(SPLIT_REGEX);
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split(SEP);
            if (parts.length != 2) {
                continue;
            }

            String op = parts[0].trim().toLowerCase();
            String amountStr = parts[1].trim();

            if (!SUPPLY.equals(op) && !BUY.equals(op)) {
                continue;
            }

            int amount;
            try {
                amount = Integer.parseInt(amountStr);
            } catch (NumberFormatException ex) {
                continue;
            }

            if (SUPPLY.equals(op)) {
                sumSupply += amount;
            } else {
                sumBuy += amount;
            }
        }

        return new Totals(sumSupply, sumBuy);
    }

    private String buildReport(Totals t) {
        int result = t.supply - t.buy;

        StringBuilder sb = new StringBuilder();
        sb.append(SUPPLY).append(SEP).append(t.supply).append(NEW_LINE)
                .append(BUY).append(SEP).append(t.buy).append(NEW_LINE)
                .append(RESULT).append(SEP).append(result).append(NEW_LINE);

        return sb.toString();
    }

    private void writeFile(String toFileName, String report) {
        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
