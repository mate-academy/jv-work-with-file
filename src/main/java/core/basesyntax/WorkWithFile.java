package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = "\\W+";
    private static final String DELIMITER_FOR_BUILDER = ",";
    private static final String INDEX_FIRST = "supply";
    private static final String INDEX_SECOND = "buy";
    private static final String INDEX_THIRD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String readDataFromFile = readFromFile(fromFileName);
        String report = prepareReport(readDataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder infoForReadingFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                infoForReadingFromFile.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can`t be read from file " + fromFileName, e);
        }
        return infoForReadingFromFile.toString();
    }

    private String prepareReport(String infoFromReadingFromFile) {
        StringBuilder convertedDataFromFile = new StringBuilder();
        String[] arrayOfInputData = infoFromReadingFromFile.split(DELIMITER);
        int buy = 0;
        int supply = 0;
        for (int i = 0; i < arrayOfInputData.length; i++) {
            if (arrayOfInputData[i].equals(INDEX_FIRST)) {
                supply = supply + Integer.parseInt(arrayOfInputData[i + 1]);
            }
            if (arrayOfInputData[i].equals(INDEX_SECOND)) {
                buy = buy + Integer.parseInt(arrayOfInputData[i + 1]);
            }
        }
        convertedDataFromFile.append(INDEX_FIRST).append(DELIMITER_FOR_BUILDER).append(supply)
                .append(System.lineSeparator());
        convertedDataFromFile.append(INDEX_SECOND).append(DELIMITER_FOR_BUILDER).append(buy)
                .append(System.lineSeparator());
        convertedDataFromFile.append(INDEX_THIRD).append(DELIMITER_FOR_BUILDER)
                .append(supply - buy);
        return convertedDataFromFile.toString();
    }

    private void writeToFile(String actualResult, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(actualResult);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName, e);
        }
    }
}
