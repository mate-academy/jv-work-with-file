package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String RESULT = "result";
    private static final String[] OPERATION_TYPES = {"supply", "buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeDataToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString().split("\\W+");
    }

    private String createReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int[] amount = getAmount(data);
        report.append(OPERATION_TYPES[SUPPLY_INDEX]).append(',')
                .append(amount[SUPPLY_INDEX]).append(System.lineSeparator())
                .append(OPERATION_TYPES[BUY_INDEX]).append(',')
                .append(amount[BUY_INDEX]).append(System.lineSeparator())
                .append(RESULT).append(',')
                .append(amount[SUPPLY_INDEX] - amount[BUY_INDEX]);
        return report.toString();
    }

    private int[] getAmount(String[] data) {
        int[] amount = new int[OPERATION_TYPES.length];
        for (int i = 0; i < data.length; i += 2) {
            if (data[i].equals(OPERATION_TYPES[SUPPLY_INDEX])) {
                amount[SUPPLY_INDEX] += Integer.parseInt(data[i + 1]);
            } else {
                amount[BUY_INDEX] += Integer.parseInt(data[i + 1]);
            }
        }
        return amount;
    }

    private void writeDataToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
