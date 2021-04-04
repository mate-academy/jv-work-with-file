package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final int[] amountArray = new int[2];
    private final String[] operationTypeArray = new String[] {"supply", "buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(toFileName))) {
            fillamountArray(fromFileName);
            writter.write(operationTypeArray[0] + ',' + amountArray[0] + System.lineSeparator());
            writter.write(operationTypeArray[1] + ',' + amountArray[1] + System.lineSeparator());
            writter.write("result," + Math.abs(amountArray[0] - amountArray[1]));
        } catch (IOException error) {
            throw new RuntimeException("Can't write data to file "
                    + fromFileName, error);
        }
    }

    private void fillamountArray(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] content = line.split(",");
                String operation = content[0];
                int amount = Integer.parseInt(content[1]);
                if (operation.equals(operationTypeArray[0])) {
                    amountArray[0] += amount;
                } else if (operation.equals(operationTypeArray[1])) {
                    amountArray[1] += amount;
                }
            }
        } catch (IOException error) {
            throw new RuntimeException("Can't read data from file "
                    + fromFileName, error);
        }
    }
}
