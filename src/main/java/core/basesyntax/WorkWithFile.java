package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_ACTION = "supply";
    private static final String BUY_ACTION = "buy";
    private static final String RESULT = "result";
    private static final String[] OUTPUT_FIELDS = {SUPPLY_ACTION, BUY_ACTION, RESULT};
    private static final String SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataEntries = getContentFromFile(fromFileName);
        int[] totals = new int[OUTPUT_FIELDS.length];

        for (String entry : dataEntries) {
            String[] entryData = entry.split(SEPARATOR);
            String action = entryData[OPERATION_INDEX];
            int amount = Integer.parseInt(entryData[AMOUNT_INDEX]);
            int actionIndex = getActionIndex(action);
            int resultIndex = getActionIndex(RESULT);

            totals[actionIndex] += amount;
            if (action.equals(SUPPLY_ACTION)) {
                totals[resultIndex] += amount;
            } else {
                totals[resultIndex] -= amount;
            }
        }
        writeContentToFile(totals, toFileName);
    }

    private String[] getContentFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String currentLine = bufferedReader.readLine();
            builder.append(currentLine);
            while ((currentLine = bufferedReader.readLine()) != null) {
                builder.append(LINE_SEPARATOR).append(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }

        return builder.toString().split(LINE_SEPARATOR);
    }

    private int getActionIndex(String actionName) {
        for (int i = 0; i < OUTPUT_FIELDS.length; i++) {
            if (OUTPUT_FIELDS[i].equals(actionName)) {
                return i;
            }
        }
        return -1;
    }

    private void writeContentToFile(int[] content, String fileName) {
        String result = createResultString(content);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }

    private String createResultString(int[] content) {
        StringBuilder builder = new StringBuilder();
        for (String fieldName : OUTPUT_FIELDS) {
            builder.append(fieldName)
                    .append(SEPARATOR)
                    .append(content[getActionIndex(fieldName)])
                    .append(LINE_SEPARATOR);
        }
        return builder.toString();
    }
}
