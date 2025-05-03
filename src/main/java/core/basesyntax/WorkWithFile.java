package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String COMMA = ",";
    private static final String WHITESPACE = " ";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFromFile(fromFileName);
        String reportedData = createReport(dataFromFile);
        writeDataToFile(toFileName, reportedData);
    }

    private String[] readDataFromFile(String filename) {
        File file = new File(filename);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String lineOfData = bufferedReader.readLine();
            while (lineOfData != null) {
                stringBuilder.append(lineOfData).append(WHITESPACE);
                lineOfData = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + filename, e);
        }
        if (stringBuilder.length() == 0) {
            throw new RuntimeException("There is no data to read in file " + filename);
        }
        return stringBuilder.toString().split(WHITESPACE);
    }

    private void writeDataToFile(String filename, String data) {
        File file = new File(filename);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + filename, e);
        }
    }

    private String createReport(String[] dataToReport) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String data : dataToReport) {
            String[] splittedData = data.split(COMMA);
            if (splittedData[OPERATION_INDEX].equals(OPERATION_SUPPLY)) {
                supplyAmount += Integer.parseInt(splittedData[AMOUNT_INDEX]);
            } else {
                buyAmount += Integer.parseInt(splittedData[AMOUNT_INDEX]);
            }
        }

        return new StringBuilder()
                .append(OPERATION_SUPPLY + COMMA + supplyAmount).append(System.lineSeparator())
                .append(OPERATION_BUY + COMMA + buyAmount).append(System.lineSeparator())
                .append(OPERATION_RESULT + COMMA + (supplyAmount - buyAmount))
                .toString();
    }
}
