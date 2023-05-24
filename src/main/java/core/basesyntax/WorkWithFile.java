package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFile = readFromFile(fromFileName);
        String report = createReport(readFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName + e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] values) {
        int supplySum = 0;
        int buySum = 0;
        for (String value: values) {
            String[] element = value.split(SEPARATOR);
            if (element[OPERATION_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(element[AMOUNT_INDEX]);
            }
            if (element[OPERATION_INDEX].equals("buy")) {
                buySum += Integer.parseInt(element[AMOUNT_INDEX]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator());
        stringBuilder.append("buy,").append(buySum).append(System.lineSeparator());
        stringBuilder.append("result,").append(result).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);

        } catch (IOException ex) {
            throw new RuntimeException("Can't write data to file " + fileName, ex);
        }
    }
}
