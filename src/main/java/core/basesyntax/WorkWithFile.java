package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file:" + fromFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int buySummary = 0;
        int supplySummary = 0;
        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] dataComponents = line.split(COMMA);
            String operationType = dataComponents[OPERATION_TYPE_INDEX];
            int money = Integer.parseInt(dataComponents[AMOUNT_INDEX]);
            if (operationType.equals(BUY)) {
                buySummary = buySummary + money;
            }
            if (operationType.equals(SUPPLY)) {
                supplySummary = supplySummary + money;
            }
        }
        int result = supplySummary - buySummary;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supplySummary).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySummary).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return report.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File fileReport = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileReport))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file:" + toFileName, e);
        }
    }
}
