package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    private static final String[] STATISTICS_FIELDS = {"supply", "buy", "result"};
    private static final int SUPPLY_POSITION = 0;
    private static final int BUY_POSITION = 1;
    private static final int RESULT_POSITION = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        int[] statisticsValues = calculateStatistics(fileContent);
        writeToFile(statisticsValues, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder sb = new StringBuilder();

        try (FileReader reader = new FileReader(fromFileName);
                BufferedReader bufferedReader = new BufferedReader(reader)) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                sb.append(readLine).append(System.lineSeparator());
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while reading a file");
        }

        return sb.toString().trim();
    }

    private int[] calculateStatistics(String fileContent) {
        int[] statisticsValues = new int[3];

        Scanner scanner = new Scanner(fileContent);
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            if (line[0].equals(STATISTICS_FIELDS[SUPPLY_POSITION])) {
                statisticsValues[SUPPLY_POSITION] += Integer.parseInt(line[1]);
            } else {
                statisticsValues[BUY_POSITION] += Integer.parseInt(line[1]);
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
            System.out.println("Error while writing into a file");
        }

    }
}
