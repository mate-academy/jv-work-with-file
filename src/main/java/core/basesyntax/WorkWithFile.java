package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR_OF_DATA = ",";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String SUPPLY_DATA = "supply";
    private static final String BUY_DATA = "buy";
    private static final String RESULT_DATA = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(toFileName, getReport(readFromFile(fromFileName)));
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String dataFromFile = bufferedReader.readLine();
            while (dataFromFile != null) {
                builder.append(dataFromFile).append(System.lineSeparator());
                dataFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read file" + fileName, e);
        }
        return builder.toString();
    }

    private String getReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] arrayWithDataFromFile = dataFromFile.split(System.lineSeparator());
        for (String string : arrayWithDataFromFile) {
            String[] valueWithAmount = string.split(SEPARATOR_OF_DATA);
            if (valueWithAmount[INDEX_OF_OPERATION_TYPE].equals(SUPPLY_DATA)) {
                supply += Integer.parseInt(valueWithAmount[INDEX_OF_AMOUNT]);
            } else {
                buy += Integer.parseInt(valueWithAmount[INDEX_OF_AMOUNT]);
            }
        }
        int result = supply - buy;
        return createReportString(supply, buy, result);
    }

    private String createReportString(int supply, int buy, int result) {
        StringBuilder resultBuilder = new StringBuilder();
        return resultBuilder
                .append(SUPPLY_DATA)
                .append(SEPARATOR_OF_DATA)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY_DATA)
                .append(SEPARATOR_OF_DATA)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT_DATA)
                .append(SEPARATOR_OF_DATA)
                .append(result)
                .toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file: " + toFileName, e);
        }
    }
}
