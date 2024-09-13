package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readData(fromFileName);
        writeData(toFileName, statistics[0], statistics[1]);
    }

    private int[] readData(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] element = line.split(",");
                String operationType = element[0];
                int amount = Integer.parseInt(element[1]);

                if (operationType.equals("supply")) {
                    supply += amount;
                } else if (operationType.equals("buy")) {
                    buy += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found:" + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file " + fromFileName, e);
        }
        return new int[]{supply, buy};
    }

    private void writeData(String toFileName, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + (supply - buy) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file: " + toFileName, e);
        }
    }
}
