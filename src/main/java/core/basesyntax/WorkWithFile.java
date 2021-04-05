package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String[] CSV_CATEGORIES = new String[]{"buy"};
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToCsvFile(toFileName, readFromCsvFile(fromFileName));
    }

    private String readFromCsvFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        List<String> csvValues;
        int buyTotal = 0;
        int supplyTotal = 0;
        try {
            csvValues = Files.readAllLines(fileFrom.toPath());
            for (String currentLine : csvValues) {
                if (currentLine.contains(CSV_CATEGORIES[0])) {
                    buyTotal += Integer.parseInt(currentLine
                            .split(DELIMITER)[CSV_CATEGORIES.length]);
                    continue;
                }
                supplyTotal += Integer.parseInt(currentLine
                        .split(DELIMITER)[CSV_CATEGORIES.length]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return "supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator() + "result,"
                + (supplyTotal - buyTotal) + System.lineSeparator();
    }

    private void writeToCsvFile(String toFileName, String csvData) {
        File fileTo = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo, true))) {
            bufferedWriter.write(csvData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
