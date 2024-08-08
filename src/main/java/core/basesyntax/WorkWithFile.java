package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_OF_KIND = 0;
    public static final int INDEX_OF_SPENDINGS = 1;
    public static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName, toFileName);
    }

    public void readDataFromFile(String fromFile, String toFile) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] linesOfFile = line.split(SEPARATOR);
                if (linesOfFile[INDEX_OF_KIND].equalsIgnoreCase("supply")) {
                    supplySum += Integer.parseInt(linesOfFile[INDEX_OF_SPENDINGS]);
                } else {
                    buySum += Integer.parseInt(linesOfFile[INDEX_OF_SPENDINGS]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t read from file", e);
        }
        writeDataToFile(toFile, supplySum, buySum);
    }

    public void writeDataToFile(String toFile, int supplySum, int buySum) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write("supply," + supplySum + System.lineSeparator());
            bufferedWriter.write("buy," + buySum + System.lineSeparator());
            bufferedWriter.write("result," + (supplySum - buySum));
        } catch (IOException e) {
            throw new RuntimeException("can`t write to file", e);
        }
    }
}
