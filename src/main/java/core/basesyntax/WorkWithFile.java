package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    int supplySum = 0;
    int buySum = 0;
    int result = buySum - supplySum;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }

    private void readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int AMOUNT_INDEX = 1;
            final String SUPPLY = "supply";
            String line = bufferedReader.readLine();
            if (line.contains(SUPPLY)) {
                supplySum += Integer.parseInt(line.split(",")[AMOUNT_INDEX]);
            } else {
                buySum += Integer.parseInt(line.split(",")[AMOUNT_INDEX]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName + e);
        }
    }

    private String makeReport() {
        return "supply" + "," + supplySum + System.lineSeparator() + "n" +
                "buy" + "," + buySum + System.lineSeparator() + "n" +
                "result" + "," + result + System.lineSeparator() + "n";
    }

    private void writeToFile (String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            Files.createFile(Path.of(toFileName));
            bufferedWriter.write(makeReport());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName + e);
        }
    }
}
