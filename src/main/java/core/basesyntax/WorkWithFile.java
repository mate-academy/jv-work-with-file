package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String WHITESPACE = " ";
    private static final String SUPPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";
    private static final String RESULT_NAME = "result";
    private static final int STRING_NAME = 0;
    private static final int STRING_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String reportString = generateReport(dataFromFile);
        writeToFile(toFileName, reportString);
    }

    public String[] readFile(String fromFileName) {
        String result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (value != null) {
                result = String.valueOf(stringBuilder.append(value).append(WHITESPACE));
                value = reader.readLine();
            }
            String[] stringArray = result.split(WHITESPACE);
            System.out.println(stringBuilder);
            return stringArray;
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
    }

    public String generateReport(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        int result;
        for (String value : dataFromFile) {
            String[] operation = value.split(COMMA);
            String operationType = operation[STRING_NAME];
            int operationAmount = Integer.parseInt(operation[STRING_AMOUNT]);
            if (operationType.equals(SUPPLY_TEXT)) {
                supply += operationAmount;
            }
            if (operationType.equals(BUY_TEXT)) {
                buy += operationAmount;
            }
        }
        result = supply - buy;
        return SUPPLY_TEXT + COMMA + supply + System.lineSeparator()
                + BUY_TEXT + COMMA + buy + System.lineSeparator()
                + RESULT_NAME + COMMA + result;
    }

    public void writeToFile(String toFileName, String reportString) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(String.valueOf(reportString));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }
}

