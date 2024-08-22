package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int INDEX_OF_KIND = 0;
    public static final int INDEX_OF_SPENDINGS = 1;
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";
    public static final String SEPARATOR = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readDataFromFile(fromFileName);
        String report = generateReport(data);
        writeDataToFile(toFileName, report);
    }

    private String readDataFromFile(String fromFile) {
        int supplySum = 0;
        int buySum = 0;
        String result;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] linesOfFile = line.split(COMMA);
                if (linesOfFile[INDEX_OF_KIND].equalsIgnoreCase(SUPPLY)) {
                    supplySum += Integer.parseInt(linesOfFile[INDEX_OF_SPENDINGS]);
                } else {
                    buySum += Integer.parseInt(linesOfFile[INDEX_OF_SPENDINGS]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("can`t read from file", e);
        }
        return result = supplySum + SEPARATOR + buySum;
    }

    private String generateReport(String data) {
        String[] regex = data.split(SEPARATOR);
        return new StringBuilder()
                .append(SUPPLY)
                .append(COMMA)
                .append(regex[INDEX_OF_KIND])
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(regex[INDEX_OF_SPENDINGS])
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(Integer.parseInt(regex[INDEX_OF_KIND])
                        - Integer.parseInt(regex[INDEX_OF_SPENDINGS])).toString();
    }

    private static void writeDataToFile(String toFile, String result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(String.valueOf(result));
        } catch (IOException e) {
            throw new RuntimeException("can`t write to file", e);
        }
    }
}
