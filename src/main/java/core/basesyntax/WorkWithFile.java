package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITING_SYMBOL = ",";
    private static final int AMOUNT_OF_OPERATIONS_TYPES = 2;
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_OPERATION_VOLUE = 1;
    private static final String[] OPERATION_TYPES = {"supply", "buy"};
    private static final int INDEX_OF_TOTAL_SUPPLY = 0;
    private static final int INDEX_OF_TOTAL_BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                data.append(nextLine).append(System.lineSeparator());
                nextLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return data.toString();
    }

    private String createReport(String inputData) {
        String[] lines = inputData.split(System.lineSeparator());
        int[] total = new int[AMOUNT_OF_OPERATIONS_TYPES];
        for (String line : lines) {
            String[] lineData = line.split(DELIMITING_SYMBOL);
            for (int typeIndex = 0; typeIndex < AMOUNT_OF_OPERATIONS_TYPES; typeIndex++) {
                if (lineData[INDEX_OF_OPERATION_TYPE].equals(OPERATION_TYPES[typeIndex])) {
                    total[typeIndex] += Integer.parseInt(lineData[INDEX_OF_OPERATION_VOLUE]);
                }
            }
        }
        StringBuilder report = new StringBuilder();
        for (int typeIndex = 0; typeIndex < AMOUNT_OF_OPERATIONS_TYPES; typeIndex++) {
            report.append(OPERATION_TYPES[typeIndex]).append(DELIMITING_SYMBOL)
                    .append(total[typeIndex]).append(System.lineSeparator());
        }
        int result = total[INDEX_OF_TOTAL_SUPPLY] - total[INDEX_OF_TOTAL_BUY];
        report.append("result").append(DELIMITING_SYMBOL).append(result);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
