package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private final int[] countOccurrences = new int[2];

    private void getOccurrences(String fromFile) {
        File inputFrom = new File(fromFile);
        final String supplyLine = "supply";
        final String buyLine = "buy";
        int countSupply = 0;
        int countBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFrom))) {
            String lineFromFile = reader.readLine();
            while (lineFromFile != null) {
                if (lineFromFile.startsWith(supplyLine)) {
                    countSupply += Integer.parseInt(
                            lineFromFile.substring(lineFromFile.indexOf(',') + 1));
                }
                if (lineFromFile.startsWith(buyLine)) {
                    countBuy += Integer.parseInt(
                            lineFromFile.substring(lineFromFile.indexOf(',') + 1));
                }
                lineFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        countOccurrences[0] = countSupply;
        countOccurrences[1] = countBuy;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        getOccurrences(fromFileName);

        String fullReport = "supply," + countOccurrences[0] + System.lineSeparator()
                + "buy," + countOccurrences[1] + System.lineSeparator()
                + "result," + (countOccurrences[0] - countOccurrences[1]);

        File writeResult = new File(toFileName);

        try {
            writeResult.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }

        try (BufferedWriter writeToFile = new BufferedWriter(new FileWriter(writeResult))) {
            writeToFile.write(fullReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
