package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COMMA = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String resultingData = createReport(dataFromFile);
        writeToFile(toFileName, resultingData);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(LINE_SEPARATOR);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read a file: " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        StringBuilder builder = new StringBuilder();
        String[] separatedData = data.split(LINE_SEPARATOR);
        int supplySum = 0;
        int buySum = 0;

        for (String s : separatedData) {
            String[] temp = s.split(COMMA);
            if (temp[OPERATION_INDEX].equals(SUPPLY)) {
                supplySum += Integer.parseInt(temp[AMOUNT_INDEX]);
            }
            if (temp[OPERATION_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(temp[AMOUNT_INDEX]);
            }
        }
        int result = supplySum - buySum;
        builder.append(SUPPLY).append(COMMA).append(supplySum).append(LINE_SEPARATOR)
                .append(BUY).append(COMMA).append(buySum).append(LINE_SEPARATOR)
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file: " + toFileName, e);
        }

    }
}
