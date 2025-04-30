package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COMA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INFO_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = processData(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String processData(String data) {
        String[] tables = data.split(LINE_SEPARATOR);
        int supply = 0;
        int buy = 0;
        for (String table : tables) {
            String[] info = table.split(COMA_SEPARATOR);
            String operationType = info[OPERATION_INFO_INDEX];
            int operationAmount = Integer.parseInt(info[OPERATION_AMOUNT_INDEX]);
            if (operationType.equals(SUPPLY)) {
                supply += operationAmount;
            } else if (operationType.equals(BUY)) {
                buy += operationAmount;
            }
        }
        return creatingReport(supply, buy);
    }

    private String creatingReport(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMA_SEPARATOR).append(supply)
                .append(LINE_SEPARATOR).append(BUY).append(COMA_SEPARATOR)
                .append(buy).append(LINE_SEPARATOR)
                .append(RESULT).append(COMA_SEPARATOR)
                .append(supply - buy)
                .append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName + e);
        }
    }
}
