package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SPLITTER = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String WRITE_EXCEPTION = "Can't write to file %s";
    private static final String READ_EXCEPTION = "Can't read file with name %s";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;
    private static final int OPERATION_TYPE = 0;
    private static final int OPERATION_INFO = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String table = readFile(fromFileName);
        String text = processData(table);
        writeToFile(text, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format(READ_EXCEPTION, fromFileName), e);
        }
        return stringBuilder.toString();
    }

    private String processData(String table) {
        String[] data = table.split(LINE_SEPARATOR);
        int supply = 0;
        int buy = 0;
        for (String separatedData : data) {
            String[] operationInfo = separatedData.split(COMMA_SPLITTER);
            String operationType = operationInfo[OPERATION_TYPE];
            int operationAmount = Integer.parseInt(operationInfo[OPERATION_INFO]);
            if (operationType.equals(SUPPLY)) {
                supply += operationAmount;
            } else {
                buy += operationAmount;
            }
        }
        int[] operationAmount = {supply, buy, supply - buy};
        return generateReport(operationAmount);
    }

    private String generateReport(int[] operationAmount) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA_SPLITTER).append(operationAmount[SUPPLY_INDEX])
                .append(LINE_SEPARATOR)
                .append(BUY).append(COMMA_SPLITTER).append(operationAmount[BUY_INDEX])
                .append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA_SPLITTER).append(operationAmount[RESULT_INDEX])
                .append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(String.format(WRITE_EXCEPTION, toFileName), e);
        }
    }
}
