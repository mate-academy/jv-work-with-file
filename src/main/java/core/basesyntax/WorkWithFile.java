package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(fromFileName));
    }

    private String createReport(String fileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] linesRead = line.split(CSV_SEPARATOR);
                if (linesRead[FIRST_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(linesRead[SECOND_INDEX]);
                }
                if (linesRead[FIRST_INDEX].equals(BUY)) {
                    buyAmount += Integer.parseInt(linesRead[SECOND_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from the file " + fileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder().append(SUPPLY).append(CSV_SEPARATOR)
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(CSV_SEPARATOR).append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(CSV_SEPARATOR).append(supplyAmount - buyAmount);
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly write data to the file " + fileName, e);
        }
    }
}
