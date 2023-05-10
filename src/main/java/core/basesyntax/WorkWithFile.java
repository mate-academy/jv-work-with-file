package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String ERROR_READING_FILE = "There is error reading from fileName -> %s";
    private static final String ERROR_CREATING_FILE = "There is error creating toFileName -> %s";
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_OF_KEY = 0;
    private static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String readFromFileData = readFromFile(fromFileName);
        String preparedDataForReport = getPreparedReport(readFromFileData);
        createFileReport(preparedDataForReport, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try {
            Path pathToReadFile = Path.of(fromFileName);
            return Files.readString(pathToReadFile);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_READING_FILE + fromFileName, e);
        }
    }

    private String getPreparedReport(String readFromFileData) {
        StringBuilder stringBuilderResult = new StringBuilder();
        String[] separatedStrings = readFromFileData.split(System.lineSeparator());
        int sumOfSupplies = 0;
        int sumOfBuys = 0;
        for (String elementOfArray : separatedStrings) {
            String[] splitArray = elementOfArray.split(COMMA);
            if (splitArray[INDEX_OF_KEY].equals(SUPPLY)) {
                sumOfSupplies += Integer.parseInt(splitArray[INDEX_OF_VALUE]);
            } else if (splitArray[INDEX_OF_KEY].equals(BUY)) {
                sumOfBuys += Integer.parseInt(splitArray[INDEX_OF_VALUE]);
            }
        }
        int resultNum = sumOfSupplies - sumOfBuys;
        stringBuilderResult
            .append(SUPPLY).append(COMMA).append(sumOfSupplies).append(System.lineSeparator())
            .append(BUY).append(COMMA).append(sumOfBuys).append(System.lineSeparator())
            .append(RESULT).append(COMMA).append(resultNum).append(System.lineSeparator());
        return stringBuilderResult.toString();
    }

    private void createFileReport(String stringReport, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(stringReport);
        } catch (IOException e) {
            throw new RuntimeException(String.format(ERROR_CREATING_FILE, toFileName), e);
        }
    }
}
