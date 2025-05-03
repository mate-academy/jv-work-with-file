package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_CONST = "supply";
    private static final String BUY_CONST = "buy";
    private static final String RESULT_CONST = "result";
    private static final String SPLIT_OPERATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeReport(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                dataFromFile.append(value).append(LINE_SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private String generateReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] operationsData = dataFromFile.split(LINE_SEPARATOR);
        for (String operationLine : operationsData) {
            String[] operationInfo = operationLine.split(SPLIT_OPERATOR);
            String operation = operationInfo[0];
            String amount = operationInfo[1];
            if (operation.equals(SUPPLY_CONST)) {
                supply += Integer.parseInt(amount);
            } else {
                buy += Integer.parseInt(amount);
            }
        }
        int result = supply - buy;
        StringBuilder generatedReport = new StringBuilder();
        generatedReport.append(SUPPLY_CONST).append(SPLIT_OPERATOR).append(supply)
                .append(LINE_SEPARATOR).append(BUY_CONST).append(SPLIT_OPERATOR).append(buy)
                .append(LINE_SEPARATOR).append(RESULT_CONST).append(SPLIT_OPERATOR).append(result)
                .append(LINE_SEPARATOR);
        return generatedReport.toString();
    }

    private void writeReport(String resultData, String toFileName) {
        try (BufferedWriter report = new BufferedWriter(new FileWriter(toFileName))) {
            report.write(resultData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
