package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int RESULT_ARRAY_LENGTH = 3;
    private static final String[] CSV_CATEGORIES = new String[]{"buy", "supply"};

    public void getStatistic(String fromFileName, String toFileName) {
        writeToCsvFile(toFileName, readFromCsvFile(fromFileName));
    }

    private String[] readFromCsvFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        List<String> csvValues;
        int buyTotal = 0;
        int supplyTotal = 0;
        try {
            csvValues = Files.readAllLines(fileFrom.toPath());
            for (String currentLine : csvValues) {
                if (currentLine.contains(CSV_CATEGORIES[0])) {
                    buyTotal += Integer.parseInt(currentLine.split(",")[1]);
                    continue;
                }
                supplyTotal += Integer.parseInt(currentLine.split(",")[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[] calculatedTotal = new String[RESULT_ARRAY_LENGTH];
        calculatedTotal[0] = "supply," + supplyTotal + System.lineSeparator();
        calculatedTotal[1] = "buy," + buyTotal + System.lineSeparator();
        calculatedTotal[2] = "result," + (supplyTotal - buyTotal) + System.lineSeparator();
        return calculatedTotal;
    }

    private void writeToCsvFile(String toFileName, String[] csvData) {
        File fileTo = new File(toFileName);
        for (String csvDatum : csvData) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
                bufferedWriter.write(csvDatum);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
