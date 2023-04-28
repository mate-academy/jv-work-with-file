package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int LIMIT_OF_LOOP = -1;
    private static final String SPECIFIED_CHARACTERS = "[\n\r]";
    private static final int ARRAY_INDEX = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createResultString(calculateStatistic(readFile(fromFileName))), toFileName);
    }

    private void writeToFile(String dataToWrite, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            writer.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + fileName, e);
        }
    }

    private String createResultString(int[] calculatedData) {
        StringBuilder statistic = new StringBuilder();
        statistic.append(SUPPLY).append(COMMA).append(calculatedData[0])
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(calculatedData[1])
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(calculatedData[2])
                .append(System.lineSeparator());
        return statistic.toString();
    }

    private int[] calculateStatistic(String dataFromFile) {
        int buyCounter = 0;
        int supplyCounter = 0;
        String data = dataFromFile.replaceAll(SPECIFIED_CHARACTERS, ",");
        String[] dataArray = data.split(",");
        for (int i = 0; i < dataArray.length + LIMIT_OF_LOOP; i++) {
            if (dataArray[i].equals(BUY)) {
                buyCounter += Integer.parseInt(dataArray[i + ARRAY_INDEX]);
            } else if (dataArray[i].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(dataArray[i + ARRAY_INDEX]);
            }
        }
        int result = supplyCounter - buyCounter;
        return new int[]{supplyCounter, buyCounter, result};
    }

    private String readFile(String fileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int value = reader.read();
            while (value != LIMIT_OF_LOOP) {
                fileContent.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file " + fileName, e);
        }
        return fileContent.toString();
    }
}
