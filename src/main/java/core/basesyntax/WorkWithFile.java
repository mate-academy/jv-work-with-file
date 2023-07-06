package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMA_SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT_KEYWORD = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String unformattedData = readFile(fromFileName);
        StringBuilder report = buildReport(unformattedData);
        writeToFile(toFileName, report.toString());
    }

    private String readFile(String fromFile) {
        File file = new File(fromFile);
        StringBuilder gatheredData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            if (!bufferedReader.ready()) {
                return gatheredData.toString();
            }
            String value = bufferedReader.readLine();
            while (value != null) {
                gatheredData.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return gatheredData.toString();
    }

    private StringBuilder buildReport(String unformattedData) {
        System.out.println(unformattedData);
        int supply = 0;
        int buy = 0;
        String[] gatheredData = unformattedData.split(System.lineSeparator());
        int commaIndex;
        for (String dataLine : gatheredData) {
            commaIndex = dataLine.indexOf(COMA_SEPARATOR);
            int separatedQuantityFromDataLine =
                    Integer.parseInt(dataLine.substring(commaIndex + 1));
            if (dataLine.substring(0, commaIndex).equals(BUY_OPERATION)) {
                buy += separatedQuantityFromDataLine;
            }
            if (dataLine.substring(0, commaIndex).equals(SUPPLY_OPERATION)) {
                supply += separatedQuantityFromDataLine;
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_OPERATION).append(COMA_SEPARATOR)
                .append(supply).append(System.lineSeparator());
        report.append(BUY_OPERATION).append(COMA_SEPARATOR)
                .append(buy).append(System.lineSeparator());
        report.append(RESULT_KEYWORD).append(COMA_SEPARATOR)
                .append(supply - buy).append(System.lineSeparator());
        return report;
    }

    private void writeToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
