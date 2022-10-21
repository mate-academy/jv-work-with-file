package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DATA_TYPE_INDEX = 0;
    private static final int DATA_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String report = reportCreation(fileData);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            stringBuilder = new StringBuilder();
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String reportCreation(String data) {
        int buyCounter = 0;
        int supplyCounter = 0;
        final String stringBuy = "buy";
        final String separator = ",";
        String[] fileDataArray = data.split(System.lineSeparator());
        for (String datum : fileDataArray) {
            String[] datumArray = datum.split(separator);
            if (datumArray[DATA_TYPE_INDEX].contains(stringBuy)) {
                buyCounter += Integer.parseInt(datumArray[DATA_VALUE_INDEX]);
            } else {
                supplyCounter += Integer.parseInt(datumArray[DATA_VALUE_INDEX]);
            }
        }
        return buildReport(buyCounter, supplyCounter);
    }

    private String buildReport(int bought, int supplied) {
        StringBuilder fileDataBuilder = new StringBuilder();
        final String stringBuy = "buy";
        final String stringSupply = "supply";
        final String stringResult = "result";
        final String separator = ",";
        return fileDataBuilder
                .append(stringSupply).append(separator).append(supplied)
                .append(System.lineSeparator())
                .append(stringBuy).append(separator).append(bought)
                .append(System.lineSeparator())
                .append(stringResult).append(separator).append((supplied - bought))
                .toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileName, e);
        }
    }
}
