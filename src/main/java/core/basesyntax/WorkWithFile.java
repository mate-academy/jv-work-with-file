package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = getReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lines;
            while ((lines = reader.readLine()) != null) {
                builder.append(lines).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return builder.toString().split(" ");
    }

    private String getReport(String[] dataFromFile) {
        int valueBuy = 0;
        int valueSupply = 0;
        for (String lineFromFile : dataFromFile) {
            String[] splitLine = lineFromFile.split(",");
            String operationType = splitLine[OPERATION_INDEX];
            int sum = Integer.parseInt(splitLine[NUMBER_INDEX]);
            if (operationType.equals("buy")) {
                valueBuy += sum;
            }
            if (operationType.equals("supply")) {
                valueSupply += sum;
            }
        }
        return createReport(valueBuy, valueSupply);
    }

    private String createReport(int valueBuy, int valueSupply) {
        return "supply," + valueSupply + System.lineSeparator()
                + "buy," + valueBuy + System.lineSeparator()
                + "result," + (valueSupply - valueBuy);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}