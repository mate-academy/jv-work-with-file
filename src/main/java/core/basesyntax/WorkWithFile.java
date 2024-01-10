package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int INDEX_SUPPLY = 0;
    private static final int INDEX_BUY = 1;
    private static final int INDEX_RESULT = 2;
    private static final int ZERO = 0;
    private static final int ELEMENTS_TO_WRITE = 3;
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFile) {
        StringBuilder resultReader = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                resultReader.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFile, e);
        }
        return resultReader.toString();
    }

    private String createReport(String dataFrom) {
        int[] amountsByGroup = new int[ELEMENTS_TO_WRITE];
        String[] groupName = new String[]{SUPPLY, BUY, RESULT};
        StringBuilder reports = new StringBuilder();
        String[] dataArray = dataFrom.split(System.lineSeparator());
        for (String item : dataArray) {
            countForOperations(item.split(SEPARATOR), amountsByGroup);
        }
        amountsByGroup[INDEX_RESULT] = amountsByGroup[INDEX_SUPPLY] - amountsByGroup[INDEX_BUY];
        for (int i = 0; i < groupName.length; i++) {
            reports.append(groupName[i]).append(SEPARATOR).append(amountsByGroup[i])
                    .append(System.lineSeparator());
        }
        return reports.toString();
    }

    private void writeReportToFile(String result, String toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFile, e);
        }
    }

    private void countForOperations(String[] dataFromReader, int[] arrayForSum) {
        arrayForSum[INDEX_SUPPLY] += (dataFromReader[OPERATION_INDEX].matches(SUPPLY))
                ? Integer.parseInt(dataFromReader[AMOUNT_INDEX])
                : ZERO;
        arrayForSum[INDEX_BUY] += (dataFromReader[OPERATION_INDEX].matches(BUY))
                ? Integer.parseInt(dataFromReader[AMOUNT_INDEX])
                : ZERO;
    }
}
