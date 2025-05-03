package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFile) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                data.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFile, e);
        }
        return data.toString().split(System.lineSeparator());
    }

    private String createReport(String[]dataFromFile) {
        StringBuilder report = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        for (String line : dataFromFile) {
            String[] operationInfo = line.split(SEPARATOR);
            int operationAmount = Integer.parseInt(operationInfo[1]);
            String operationType = operationInfo[0];
            if (operationType.contains(SUPPLY)) {
                supplySum += operationAmount;
            } else {
                buySum += operationAmount;
            }
        }
        int resultAmount = supplySum - buySum;
        report.append(SUPPLY).append(SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(resultAmount);
        return report.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + fileName, e);
        }
    }
}

