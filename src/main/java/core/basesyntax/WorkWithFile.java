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
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";
    private static int supplySum = 0;
    private static int buySum = 0;
    private static String result;

    public void getStatistic(String fromFileName, String toFileName) {
        supplySum = 0;
        buySum = 0;
        readDataFromFile(fromFileName);
        result = generateReport();
        writeDataToFile(toFileName, result);
    }

    private void readDataFromFile(String fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] linesOfFile = line.split(SEPARATOR);
                if (linesOfFile[INDEX_OF_KIND].equalsIgnoreCase(SUPPLY)) {
                    supplySum += Integer.parseInt(linesOfFile[INDEX_OF_SPENDINGS]);
                } else {
                    buySum += Integer.parseInt(linesOfFile[INDEX_OF_SPENDINGS]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t read from file", e);
        }
    }

    private String generateReport() {
        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supplySum - buySum).toString();

    }

    private static void writeDataToFile(String toFile, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("can`t write to file", e);
        }
    }
}
