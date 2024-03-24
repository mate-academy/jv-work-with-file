package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_REPORT_KEY = 0;
    private static final int INDEX_OF_REPORT_VALUE = 1;
    private static final String BUY_KEY = "buy";
    private static final String SUPPLY_KEY = "supply";
    private static final String RESULT_KEY = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readFileData = readFile(fromFileName);
        String report = createReport(readFileData);
        writeToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String lastLine = bufferedReader.readLine();
            while (lastLine != null) {
                fileData.append(lastLine).append(System.lineSeparator());
                lastLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read a file", e);
        }
        return fileData.toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }
    }

    private String createReport(String fileDataWithReport) {
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String reportLine : fileDataWithReport.split(System.lineSeparator())) {
            String[] splittedLine = reportLine.split(COMMA);
            String key = splittedLine[INDEX_OF_REPORT_KEY];
            int value = Integer.parseInt(splittedLine[INDEX_OF_REPORT_VALUE]);
            if (key.equals(BUY_KEY)) {
                buyAmount += value;
            } else {
                supplyAmount += value;
            }
        }
        return SUPPLY_KEY + COMMA + supplyAmount + System.lineSeparator()
                + BUY_KEY + COMMA + buyAmount + System.lineSeparator()
                + RESULT_KEY + COMMA + (supplyAmount - buyAmount);
    }
}
