package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_COL_INDEX = 0;
    private static final int AMOUNT_COL_INDEX = 1;
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
            String action = entryData[NAME_COL_INDEX];
            int amount = Integer.parseInt(entryData[AMOUNT_COL_INDEX]);

            int actionIndex = getActionIndex(action);
            int resultIndex = getActionIndex(RESULT);
            totals[actionIndex] += amount;
            totals[resultIndex] = action.equals(SUPPLY_ACTION) ? totals[resultIndex] + amount
                    : totals[resultIndex] - amount;
        }

        writeContentToFile(totals, toFileName);
    }

    private int getActionIndex(String actionName) {
        for (int i = 0; i < OUTPUT_FIELDS.length; i++) {
            if (OUTPUT_FIELDS[i].equals(actionName)) {
                return i;
            }
        }
        return -1;
    }

    private String[] getContentFromFile(String fileName) {
        File sourceFile = new File(fileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile))) {
            String currentLine = bufferedReader.readLine();
            builder.append(currentLine);

            while (true) {
                currentLine = bufferedReader.readLine();
                if (currentLine == null) {
                    break;
                }
                builder.append(LINE_SEPARATOR).append(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }

        return builder.toString().split(LINE_SEPARATOR);
    }

    private void writeContentToFile(int[] content, String fileName) {
        StringBuilder builder = new StringBuilder();
        for (String fieldName : OUTPUT_FIELDS) {
            builder.append(fieldName)
                    .append(SEPARATOR)
                    .append(content[getActionIndex(fieldName)])
                    .append(LINE_SEPARATOR);
        }

        File destFile = new File(fileName);
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destFile))
        ) {
            if (destFile.exists()) {
                bufferedWriter.write("");
            }
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
