package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEARCHED_OPERATION_1 = "supply";
    private static final String SEARCHED_OPERATION_2 = "buy";
    private static final String RESULT_WORD = "result";
    private int totalOfOperation1;
    private int totalOfOperation2;
    private int operationDifference;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalOfOperation1 = 0;
        int totalOfOperation2 = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (SEARCHED_OPERATION_1.equals(operation)) {
                    totalOfOperation1 += amount;
                } else if (SEARCHED_OPERATION_2.equals(operation)) {
                    totalOfOperation2 += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        int operationDifference = totalOfOperation1 - totalOfOperation2;
    }

    public void writeStatistic(String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SEARCHED_OPERATION_1 + "," + totalOfOperation1);
            writer.newLine();
            writer.write(SEARCHED_OPERATION_2 + "," + totalOfOperation2);
            writer.newLine();
            writer.write(RESULT_WORD + "," + operationDifference);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }
}
