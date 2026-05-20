package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> rows = readFromFile(fromFileName);
        String report = createReport(rows);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private String createReport(List<String> rows) {
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String row : rows) {
            if (row.isEmpty()) {
                continue;
            }
            String[] rowArray = row.split(",");
            if (rowArray.length < 2) {
                continue;
            }
            String operation = rowArray[0].trim();
            int amount = Integer.parseInt(rowArray[1].trim());
            if (operation.equals(BUY)) {
                buyAmount += amount;
            } else if (operation.equals(SUPPLY)) {
                supplyAmount += amount;
            }
        }
        String lineSeparator = System.lineSeparator();
        return SUPPLY + "," + supplyAmount + lineSeparator
                + BUY + "," + buyAmount + lineSeparator
                + "result," + (supplyAmount - buyAmount);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    public static void main(String[] args) {
        String fromFileName = "apple.csv";
        String toFileName = "result.txt";
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(fromFileName, toFileName);
    }
}
