package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private int supplySum = 0;
    private int buySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        try {
            Files.createFile(Path.of(toFileName));
        } catch (IOException e) {
            throw new RuntimeException("Some troubles with creating a file " + toFileName + e);
        }
        writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int amountIndex = 1;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("supply")) {
                    supplySum += Integer.parseInt(line.split(",")[amountIndex]);
                } else {
                    buySum += Integer.parseInt(line.split(",")[amountIndex]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName + e);
        }
    }

    private String makeReport() {
        return "supply" + "," + supplySum + System.lineSeparator()
                + "buy" + "," + buySum + System.lineSeparator()
                + "result" + "," + (supplySum - buySum) + System.lineSeparator();
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(makeReport());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName + e);
        }
    }
}
