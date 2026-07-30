package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readStatistics(fromFileName);
        String report = createReport(statistics[SUPPLY_INDEX], statistics[BUY_INDEX]);
        writeReport(toFileName, report);
    }

    private int[] readStatistics(String fileName) {
        int[] statistics = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                updateStatistics(statistics, line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
        return statistics;
    }

    private void updateStatistics(int[] statistics, String line) {
        String[] record = line.split(COMMA);
        int amount = Integer.parseInt(record[AMOUNT_INDEX]);
        if (SUPPLY_OPERATION.equals(record[OPERATION_INDEX])) {
            statistics[SUPPLY_INDEX] += amount;
        } else if (BUY_OPERATION.equals(record[OPERATION_INDEX])) {
            statistics[BUY_INDEX] += amount;
        }
    }

    private String createReport(int supplyAmount, int buyAmount) {
        return SUPPLY_OPERATION + COMMA + supplyAmount +
                System.lineSeparator() +
                BUY_OPERATION + COMMA + buyAmount +
                System.lineSeparator() +
                RESULT_OPERATION + COMMA + (supplyAmount - buyAmount);
    }

    private void writeReport(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }
}
