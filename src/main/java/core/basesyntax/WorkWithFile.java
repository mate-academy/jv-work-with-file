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
    private static int supplySum = 0;
    private static int buySum = 0;
    private static StringBuilder result;

    public void getStatistic(String fromFileName, String toFileName) {
        supplySum = 0;
        buySum = 0;
        readDataFromFile(fromFileName);
        generateReport();
        writeDataToFile(toFileName, result);
    }

    public void readDataFromFile(String fromFile) {
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
    }

    public void generateReport() {
        String supplyInfo = "supply," + supplySum + System.lineSeparator();
        String buyInfo = "buy," + buySum + System.lineSeparator();
        String resultCount = "result," + (supplySum - buySum);
        result = new StringBuilder()
                .append(supplyInfo)
                .append(buyInfo)
                .append(resultCount);
    }

    public static void writeDataToFile(String toFile, StringBuilder result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("can`t write to file", e);
        }
    }
}
