package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] STATISTICS_FIELDS = {"supply", "buy", "result"};
    private static final int SUPPLY_POSITION = 0;
    private static final int BUY_POSITION = 1;
    private static final int RESULT_POSITION = 2;

    //incoming data contents looks like: name, value
    //so position 0 is name, position 1 is value
    private static final int NAME_POSITION = 0;
    private static final int VALUE_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        int[] statisticsValues = calculateStatistics(fileContent);
        writeToFile(statisticsValues, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();

        try (FileReader reader = new FileReader(fromFileName);
                BufferedReader bufferedReader = new BufferedReader(reader)) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                fileContent.append(readLine).append(System.lineSeparator());
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading a file", e);
        }

        return fileContent.toString().trim();
    }

    private int[] calculateStatistics(String fileContent) {
        int[] statisticsValues = new int[3];

        for (String line : fileContent.split(System.lineSeparator())) {
            String[] parsedLine = line.split(",");
            if (parsedLine[NAME_POSITION].equals(STATISTICS_FIELDS[SUPPLY_POSITION])) {
                statisticsValues[SUPPLY_POSITION] += Integer.parseInt(parsedLine[VALUE_POSITION]);
            } else {
                statisticsValues[BUY_POSITION] += Integer.parseInt(parsedLine[VALUE_POSITION]);
            }
        }
        statisticsValues[RESULT_POSITION] =
                statisticsValues[SUPPLY_POSITION] - statisticsValues[BUY_POSITION];

        return statisticsValues;
    }

    private void writeToFile(int[] statisticsValues, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (int i = 0; i < statisticsValues.length; i++) {
                bufferedWriter.write(STATISTICS_FIELDS[i] + ","
                        + statisticsValues[i] + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while writing into a file", e);
        }

    }
}
