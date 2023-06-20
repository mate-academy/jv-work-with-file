package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_NUMBER = 0;
    private static final int OPERATION_VALUE_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFile(fromFileName)), toFileName);
    }

    private String[] readFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String value = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file!", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(String[] linesFromFile) {
        int supplySum = 0;
        int buySum = 0;
        for (String line : linesFromFile) {
            String[] dividedLine = line.split(",");
            String operationType = dividedLine[OPERATION_TYPE_NUMBER];
            int operationValue = Integer.parseInt(dividedLine[OPERATION_VALUE_NUMBER]);
            if (operationType.equals("supply")) {
                supplySum += operationValue;
            } else if (operationType.equals("buy")) {
                buySum += operationValue;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator()).append("result,")
                .append(supplySum - buySum).append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeToFile(String message, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file!", e);
        }
    }
}
