package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITING_SYMBOL = ",";
    private static final int AMOUNT_OF_OPERATIONS_TYPES = 2;
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_OPERATION_VOLUE = 1;
    private static final String[] OPERATION_TYPES = {"supply", "buy"};
    private static final int INDEX_OF_TOTAL_SUPPLY = 0;
    private static final int INDEX_OF_TOTAL_BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] total = new int[AMOUNT_OF_OPERATIONS_TYPES];
        File fromFile = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                String[] currentData = nextLine.split(DELIMITING_SYMBOL);
                for (int i = 0; i < AMOUNT_OF_OPERATIONS_TYPES; i++) {
                    if (currentData[INDEX_OF_OPERATION_TYPE].equals(OPERATION_TYPES[i])) {
                        total[i] += Integer.parseInt(currentData[INDEX_OF_OPERATION_VOLUE]);
                    }
                }
                nextLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            for (int i = 0; i < AMOUNT_OF_OPERATIONS_TYPES; i++) {
                bufferedWriter.write(OPERATION_TYPES[i] + DELIMITING_SYMBOL
                        + total[i] + System.lineSeparator());
            }
            int result = total[INDEX_OF_TOTAL_SUPPLY] - total[INDEX_OF_TOTAL_BUY];
            bufferedWriter.write("result" + DELIMITING_SYMBOL + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
