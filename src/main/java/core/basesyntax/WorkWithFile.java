package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMETER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] info = readData(fromFileName);
        String report = createReport(info);
        writeToFile(toFileName, report);
    }

    private String[] readData(String fileName) {
        StringBuilder dataReader = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                dataReader.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file ", e);
        }
        return dataReader.toString().split(System.lineSeparator());
    }

    private String createReport(String[] info) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder report = new StringBuilder();
        for (String line : info) {
            String[] operations = line.split(DELIMETER);
            String operation = operations[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(operations[AMMOUNT_INDEX]);
            if (operation.equals(SUPPLY)) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        int resultSum = supplyAmount - buyAmount;
        return report.append(SUPPLY).append(DELIMETER).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(DELIMETER).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(DELIMETER).append(resultSum).toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to this file", e);
        }
    }
}
