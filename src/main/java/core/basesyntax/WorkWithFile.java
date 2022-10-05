package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final int BUY_INDEX = 0;
    private static final int SUPPLY_INDEX = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(getValues(readFromFile(fromFileName))));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create a report for file: " + fromFileName, e);
        }

        return builder.toString();
    }

    private int[] getValues(String infoFromFile) {
        int[] values = {0,0};
        String[] fileValue = infoFromFile.split(System.lineSeparator());

        for (String line : fileValue) {
            String[] lineValue = line.split(SEPARATOR);
            switch (lineValue[OPERATION_TYPE_INDEX]) {
                case "buy":
                    values[BUY_INDEX] += Integer.parseInt(lineValue[AMMOUNT_INDEX]);
                    break;
                case "supply":
                    values[SUPPLY_INDEX] += Integer.parseInt(lineValue[AMMOUNT_INDEX]);
                    break;
                default:
                    break;

            }
        }

        return values;
    }

    private void writeToFile(String toFileName, String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    private String createReport(int[] values) {
        return "supply," + values[SUPPLY_INDEX] + System.lineSeparator()
                + "buy," + values[BUY_INDEX] + System.lineSeparator()
                + "result," + (values[SUPPLY_INDEX] - values[BUY_INDEX]);
    }
}
