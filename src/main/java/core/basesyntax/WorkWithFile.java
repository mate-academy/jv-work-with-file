package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int RESULT_ARRAY_LENGTH = 3;
    private static final String[] CSV_CATEGORIES = new String[]{"buy", "supply"};

    public void getStatistic(String fromFileName, String toFileName) {
    }

    private String[] readFromCsvFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        List<String> csv_values;
        int buyTotal = 0;
        int supplyTotal = 0;

        try {
            csv_values = Files.readAllLines(fileFrom.toPath());
            for (String currentLine : csv_values) {
                if (currentLine.contains(CSV_CATEGORIES[0])) {
                    buyTotal += Integer.parseInt(currentLine.split(",")[1]);
                    continue;
                }
                supplyTotal += Integer.parseInt(currentLine.split(",")[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] CalculatedTotal = new String[RESULT_ARRAY_LENGTH];
        CalculatedTotal[0] = "supply," + supplyTotal + System.lineSeparator();
        CalculatedTotal[1] = "buy," + buyTotal + System.lineSeparator();
        CalculatedTotal[2] = "result," + (supplyTotal - buyTotal) + System.lineSeparator();
        return CalculatedTotal;
    }
}
