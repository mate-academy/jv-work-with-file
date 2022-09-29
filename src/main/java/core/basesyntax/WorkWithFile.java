package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final int OPERATION_TYPE_COLUMN_INDEX = 0;
    private static final int AMOUNT_COLUMN_INDEX = 1;
    private static final String SUPPLY_STRING = "supply";
    private static final String BUE_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY_STRING = "";
    private final StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName);
        createReportAndWriteToFile(toFileName);
    }

    private void readDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String readedLine = bufferedReader.readLine();
            String lineSeparator = EMPTY_STRING;
            while (readedLine != null) {
                stringBuilder.append(lineSeparator).append(readedLine);
                readedLine = bufferedReader.readLine();
                lineSeparator = LINE_SEPARATOR;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void createReportAndWriteToFile(String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String[] readingContent = stringBuilder.toString().split(LINE_SEPARATOR);
        for (String row : readingContent) {
            String[] rowInAray = row.split(DATA_SEPARATOR);
            String operationType = rowInAray[OPERATION_TYPE_COLUMN_INDEX];
            int amount = Integer.parseInt(rowInAray[AMOUNT_COLUMN_INDEX]);
            if (operationType.equals(SUPPLY_STRING)) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(SUPPLY_STRING + DATA_SEPARATOR + supplyAmount + LINE_SEPARATOR);
            bufferedWriter.write(BUE_STRING + DATA_SEPARATOR + buyAmount + LINE_SEPARATOR);
            bufferedWriter.write(RESULT_STRING + DATA_SEPARATOR + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
