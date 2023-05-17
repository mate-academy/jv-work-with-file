package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] STATISTICS_FIELDS = {"supply", "buy", "result"};
    private static final int SUPPLY_POS = 0;
    private static final int BUY_POS = 1;
    private static final int RESULT_POS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(readFromFile(fromFileName), toFileName);
    }

    private int[] readFromFile(String fromFileName) {
        int[] statisticsValues = new int[3];

        try (FileReader reader = new FileReader(fromFileName);
                BufferedReader bufferedReader = new BufferedReader(reader)) {
            String readLine = bufferedReader.readLine();
            String[] readValue;

            while (readLine != null) {
                readValue = readLine.split(",");
                if (readValue[0].equals(STATISTICS_FIELDS[SUPPLY_POS])) {
                    statisticsValues[0] += Integer.parseInt(readValue[1]);
                } else {
                    statisticsValues[1] += Integer.parseInt(readValue[1]);
                }
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error while reading a file");
        }
        statisticsValues[RESULT_POS] = statisticsValues[SUPPLY_POS] - statisticsValues[BUY_POS];

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
