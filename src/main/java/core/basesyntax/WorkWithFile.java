package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY_IDENTIFIER = "supply";
    private static final String BUY_IDENTIFIER = "buy";
    private static final String RESULT_IDENTIFIER = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(getDataFromFile(fromFileName), toFileName);
    }

    private void writeDataToFile(String data, String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String getDataFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine()).append(";");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return calculateData(stringBuilder.toString());
    }

    private String calculateData(String data) {
        int supplyValue = 0;
        int buyValue = 0;
        int indexOfSeparator = 0;
        String[] splitedData = data.split(";");
        for (String lineOfData : splitedData) {
            indexOfSeparator = lineOfData.indexOf(SEPARATOR);
            if (lineOfData.contains(SUPPLY_IDENTIFIER)) {
                supplyValue += getIntDataFromString(lineOfData, indexOfSeparator);
            } else if (lineOfData.contains(BUY_IDENTIFIER)) {
                buyValue += getIntDataFromString(lineOfData, indexOfSeparator);
            } else {
                throw new RuntimeException("Wrong data");
            }
        }
        return createResultString(supplyValue, buyValue, supplyValue - buyValue);
    }

    private int getIntDataFromString(String data, int indexOfSeparator) {
        return Integer.parseInt(data.substring(indexOfSeparator + 1));
    }

    private String createResultString(int supplyValue, int buyValue, int resultValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_IDENTIFIER)
                .append(SEPARATOR)
                .append(supplyValue)
                .append(System.lineSeparator())
                .append(BUY_IDENTIFIER)
                .append(SEPARATOR)
                .append(buyValue)
                .append(System.lineSeparator())
                .append(RESULT_IDENTIFIER)
                .append(SEPARATOR)
                .append(resultValue)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
