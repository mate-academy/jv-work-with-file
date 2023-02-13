package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String DELIMITER = ",";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotalAmount = 0;
        int buyTotalAmount = 0;
        for (String line: readFromFile(fromFileName).split(System.lineSeparator())) {
            String[] row = line.split(DELIMITER);
            if (row[OPERATION_INDEX].equals("supply")) {
                supplyTotalAmount += Integer.valueOf(row[AMOUNT_INDEX]);
            } else {
                buyTotalAmount += Integer.valueOf(row[AMOUNT_INDEX]);
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("supply,").append(supplyTotalAmount).append(System.lineSeparator())
            .append("buy,").append(buyTotalAmount).append(System.lineSeparator())
            .append("result,").append((supplyTotalAmount - buyTotalAmount));
        printToFile(toFileName, result.toString());
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder result = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                result.append(value + System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
        return result.toString();
    }

    private void printToFile(String toFileName, String text) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }
}
