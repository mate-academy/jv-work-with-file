package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    private List<String> readLinesFromFile(String fromFileName) {
        Path pathOfTest = Path.of(fromFileName);
        try {
            return Files.readAllLines(pathOfTest);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
    }

    private int[] supplyAndBuy(List<String> allLines) {
        int supply = 0;
        int buy = 0;
        for (String eachLine : allLines) {
            if (eachLine.isBlank()) {
                continue;
            }
            String[] tokens = eachLine.split(",");
            if (tokens.length >= 2) {
                String trimmedToken0 = tokens[0].trim();
                String trimmedToken1 = tokens[1].trim();
                if (trimmedToken0.equals(SUPPLY_OPERATION)) {
                    supply += Integer.parseInt(trimmedToken1);
                } else if (trimmedToken0.equals(BUY_OPERATION)) {
                    buy += Integer.parseInt(trimmedToken1);
                }
            }
        }
        return new int[]{supply, buy};
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write on the file " + toFileName, ex);
        }
    }

    private String formatReport(int supply, int buy) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        int[] totals = supplyAndBuy(lines);
        String formattedResult = formatReport(totals[0], totals[1]);
        writeReport(toFileName, formattedResult);
    }
}
