package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA_SPLITTER = ",";
    private static final String SPACE = " ";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String WRITE_EXCEPTION = "Can't write to file %s";
    private static final String READ_EXCEPTION = "Can't read file with name %s";
    private final int[] types = new int[2];

    public void getStatistic(String fromFileName, String toFileName) {
        String table = readFile(fromFileName);
        String text = writeToString(processData(table));
        writeToFile(text, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(SPACE);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format(READ_EXCEPTION, fromFileName), e);
        }
        return stringBuilder.toString();
    }

    private int[] processData(String table) {
        String[] data = table.split(SPACE);
        int supply = 0;
        int buy = 0;
        for (String separatedData : data) {
            String[] operationInfo = separatedData.split(COMMA_SPLITTER);
            String operationType = operationInfo[0];
            int operationAmount = Integer.parseInt(operationInfo[1]);
            if (operationType.equals(SUPPLY)) {
                supply += operationAmount;
            } else {
                buy += operationAmount;
            }
        }
        types[0] = supply;
        types[1] = buy;
        return types;
    }

    private String writeToString(int[] operationAmount) {
        int supply = operationAmount[0];
        int buy = operationAmount[1];
        int result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA_SPLITTER).append(supply).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA_SPLITTER).append(buy).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA_SPLITTER).append(result).append(LINE_SEPARATOR);
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
