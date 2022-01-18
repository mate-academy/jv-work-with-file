package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int PRODUCT_AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String statistic = readStatisticFromFile(fromFileName);
        writeStatisticToFile(statistic, toFileName);
    }

    private String readStatisticFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int suppliedAmount = 0;
            int boughtAmount = 0;
            String line = bufferedReader.readLine();
            while (line != null) {
                String [] lineData = line.split(REGEX);
                if (lineData[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                    suppliedAmount += Integer.parseInt(lineData[PRODUCT_AMOUNT_INDEX]);
                }
                if (lineData[OPERATION_TYPE_INDEX].equals(BUY)) {
                    boughtAmount += Integer.parseInt(lineData[PRODUCT_AMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
            StringBuilder statistics = new StringBuilder();
            statistics
                    .append(SUPPLY).append(REGEX)
                    .append(suppliedAmount).append(System.lineSeparator())
                    .append(BUY).append(REGEX)
                    .append(boughtAmount).append(System.lineSeparator())
                    .append(RESULT).append(REGEX)
                    .append(suppliedAmount - boughtAmount).append(System.lineSeparator());
            return statistics.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private void writeStatisticToFile(String information, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(information);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
