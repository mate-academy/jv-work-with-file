package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_TRANSACTION_NAME = "supply";
    private static final String BUY_TRANSACTION_NAME = "buy";
    private static final String RESULT_ROW_NAME = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        String[] linesArray = readFile(fromFileName).split(System.lineSeparator());
        for (String line : linesArray) {
            String[] tableRow = line.split(",");
            if (tableRow[0].equals(SUPPLY_TRANSACTION_NAME)) {
                supplySum += Integer.parseInt(tableRow[1]);
            }
            if (tableRow[0].equals(BUY_TRANSACTION_NAME)) {
                buySum += Integer.parseInt(tableRow[1]);
            }
        }
        String[] result = {SUPPLY_TRANSACTION_NAME + "," + String.valueOf(supplySum),
                BUY_TRANSACTION_NAME + "," + String.valueOf(buySum),
                RESULT_ROW_NAME + "," + String.valueOf(supplySum - buySum)};
        writeReportToFile(toFileName, result);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private void writeReportToFile(String toFileName, String [] report) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            for (String tableRow : report) {
                bufferedWriter.write(tableRow + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file");
        }
    }
}
