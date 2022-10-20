package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int DATA_TYPE_INDEX = 0;
    private static final int DATA_VALUE_INDEX = 1;
    private static final String STRING_BUY = "buy";
    private static final String STRING_SUPPLY = "supply";
    private static final String SEPARATOR_VALUE = ",";
    private StringBuilder fileDataBuilder;

    public void getStatistic(String fromFileName, String toFileName) {
        int buyCounter = 0;
        int supplyCounter = 0;
        fileDataBuilder = new StringBuilder();
        readFromFile(fromFileName);
        String[] fileData = fileDataBuilder.toString().split(System.lineSeparator());
        for (String datum : fileData) {
            String[] arrayDatum = datum.split(SEPARATOR_VALUE);
            if (arrayDatum[DATA_TYPE_INDEX].contains(STRING_BUY)) {
                buyCounter += Integer.parseInt(arrayDatum[DATA_VALUE_INDEX]);
            } else if (arrayDatum[DATA_TYPE_INDEX].contains(STRING_SUPPLY)) {
                supplyCounter += Integer.parseInt(arrayDatum[DATA_VALUE_INDEX]);
            } else {
                throw new RuntimeException("Wrong data in file");
            }
        }
        writeToFile(toFileName, buyCounter, supplyCounter);
    }

    private void readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                fileDataBuilder.append(bufferedReader.readLine()).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeToFile(String fileName, int buyCounter, int supplyCounter) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(reportCreator(buyCounter, supplyCounter));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String reportCreator(int buyCounter, int supplyCounter) {
        fileDataBuilder = new StringBuilder();
        return fileDataBuilder
                .append(STRING_SUPPLY).append(SEPARATOR_VALUE).append(supplyCounter)
                .append(System.lineSeparator())
                .append(STRING_BUY).append(SEPARATOR_VALUE).append(buyCounter)
                .append(System.lineSeparator())
                .append("result").append(SEPARATOR_VALUE)
                .append((supplyCounter - buyCounter)).toString();
    }
}
