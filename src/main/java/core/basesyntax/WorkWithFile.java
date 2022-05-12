package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, getReport(calculateStatistic(readFromFile(fromFileName)
                .toString().split(System.lineSeparator()))));
    }

    private void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write or close the file " + toFileName, e);
        }
    }

    private String getReport(int[] values) {
        StringBuilder statisticBuilder = new StringBuilder();
        for (int i = 0; i < Operations.values().length; i++) {
            statisticBuilder.append(Operations.values()[i]).append(",").append(values[i])
                    .append(System.lineSeparator());
        }
        return statisticBuilder.toString();
    }

    private int[] calculateStatistic(String[] lines) {
        int[] amounts = {0, 0, 0};
        for (String line : lines) {
            String[] splittedLine = line.split(",");
            int amount = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            String operation = splittedLine[OPERATION_INDEX];
            if (operation.equals(Operations.values()[SUPPLY_INDEX].toString())) {
                amounts[SUPPLY_INDEX] += amount;
            }
            if (operation.equals(Operations.values()[BUY_INDEX].toString())) {
                amounts[BUY_INDEX] += amount;
            }
        }
        amounts[RESULT_INDEX] = amounts[SUPPLY_INDEX] - amounts[BUY_INDEX];
        return amounts;
    }

    private StringBuilder readFromFile(String fileName) {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder;
    }
}
