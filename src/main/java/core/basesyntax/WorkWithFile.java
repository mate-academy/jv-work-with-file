package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int EVENT_POSITION = 0;
    public static final int NUMBER_POSITION = 1;
    public static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = getEventSum(fromFileName, "buy");
        int supplySum = getEventSum(fromFileName, "supply");
        String report = createReport(buySum, supplySum);
        writeReportToFile(toFileName, report);
    }

    private int getEventSum(String fromFileName, String event) {
        int sum = 0;
        File file = new File(fromFileName);
        try (FileReader reader = new FileReader(file);
                BufferedReader bufReader = new BufferedReader(reader)) {
            while (bufReader.ready()) {
                String[] values = bufReader.readLine().split(DELIMITER);
                if (values[EVENT_POSITION].equals(event)) {
                    sum += Integer.parseInt(values[NUMBER_POSITION]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file: " + fromFileName, e);
        }
        return sum;
    }

    private void writeReportToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (FileWriter writer = new FileWriter(file);
                BufferedWriter bufWriter = new BufferedWriter(writer)) {
            bufWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write to file: " + toFileName, e);
        }
    }

    private String createReport(int buySum, int supplySum) {
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);
    }
}
