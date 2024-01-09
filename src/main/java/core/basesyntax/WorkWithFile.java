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
        int[] resultsInt = new int[ELEMENTS_TO_WRITE];
        String[] operations = new String[]{SUPPLY, BUY, RESULT};
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] datas = line.toLowerCase().split(SEPARATOR);
                countForOperations(datas, resultsInt);
            }
            resultsInt[INDEX_RESULT] = resultsInt[INDEX_SUPPLY] - resultsInt[INDEX_BUY];
            writeToFile(toFileName, operations, resultsInt);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
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

    private void writeToFile(String toFileName,String[] names, int[] amounts) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (int i = 0; i < names.length; i++) {
                bufferedWriter.write(names[i] + SEPARATOR + amounts[i]);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName, e);
        }
    }
}
