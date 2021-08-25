package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int AMOUNT = 1;
    private static final int OPERATION = 0;
    private static final int SUPPLY_AMOUNT = 0;
    private static final int BUY_AMOUNT = 1;
    private static final int AMOUNT_ARRAYS_LENGTH = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(getFileReport(readFromFile(fromFileName)), toFileName);
    }

    private int[] readFromFile(String fromFileName) {
        int[] amount = new int[AMOUNT_ARRAYS_LENGTH];
        File fromFile = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] records = line.split(",");
                if (records[OPERATION].equals(SUPPLY)) {
                    amount[SUPPLY_AMOUNT] += Integer.parseInt(records[AMOUNT]);
                } else {
                    amount[BUY_AMOUNT] += Integer.parseInt(records[AMOUNT]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
        return amount;
    }

    private String getFileReport(int[] amount) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(amount[SUPPLY_AMOUNT])
                .append(System.lineSeparator())
                .append(BUY).append(",").append(amount[BUY_AMOUNT]).append(System.lineSeparator())
                .append(RESULT).append(",").append(amount[SUPPLY_AMOUNT] - amount[BUY_AMOUNT]);
        return report.toString();
    }

    private void writeToFile(String fileReport, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile, true))) {
            bufferedWriter.write(fileReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }
}
