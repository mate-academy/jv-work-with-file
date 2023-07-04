package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int ZERO_VALUE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        File myFile = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file [" + fileName + "].", e);
        }
        return parseFileData(builder.toString());
    }

    private String[] parseFileData(String fileData) {
        if (fileData.length() == ZERO_VALUE) {
            return new String[ZERO_VALUE];
        }
        return fileData.split(" ");
    }

    private String createReport(String[] data) {
        int supplyNumber = calculateSum(SUPPLY_NAME, data);
        int buyNumber = calculateSum(BUY_NAME, data);
        return createReportString(supplyNumber, buyNumber);
    }

    private int calculateSum(String operationName, String[] data) {
        int result = 0;
        for (String line : data) {
            String[] splitLine = line.split(",");
            if (splitLine[KEY_INDEX].equals(operationName)) {
                result += Integer.parseInt(splitLine[VALUE_INDEX]);
            }
        }
        return result;
    }

    private String createReportString(int supplyNumber, int buyNumber) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_NAME).append(",").append(supplyNumber)
                .append(System.lineSeparator())
                .append(BUY_NAME).append(",").append(buyNumber)
                .append(System.lineSeparator())
                .append("result,").append(supplyNumber - buyNumber);
        return builder.toString();
    }

    private void writeToFile(String fileName, String data) {
        File myFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(myFile))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file [" + fileName + "].", e);
        }
    }
}
