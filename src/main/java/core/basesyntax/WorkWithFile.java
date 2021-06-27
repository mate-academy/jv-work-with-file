package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        File writeResult = new File(toFileName);

        try {
            writeResult.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }

        try (BufferedWriter writeToFile = new BufferedWriter(new FileWriter(writeResult, true))) {
            writeToFile.write("supply," + getSupply(fromFileName) + System.lineSeparator()
                    + "buy," + getBuy(fromFileName) + System.lineSeparator()
                    + "result," + (getSupply(fromFileName) - getBuy(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private int getSupply(String fromFile) {
        File inputFrom = new File(fromFile);
        final String Supply_Line = "supply";
        int supply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFrom))) {
            String lineFromFile = reader.readLine();
            while (lineFromFile != null) {
                if (lineFromFile.startsWith(Supply_Line)) {
                    supply += Integer.parseInt(
                            lineFromFile.substring(lineFromFile.indexOf(',') + 1));
                }
                lineFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return supply;
    }

    private int getBuy(String fromFile) {
        File inputFrom = new File(fromFile);
        final String Buy_Line = "buy";
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFrom))) {
            String lineFromFile = reader.readLine();
            while (lineFromFile != null) {
                if (lineFromFile.startsWith(Buy_Line)) {
                    buy += Integer.parseInt(lineFromFile.substring(lineFromFile.indexOf(',') + 1));
                }
                lineFromFile = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return buy;
    }
}
