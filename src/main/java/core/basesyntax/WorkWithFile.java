package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private int supplyAmount;
    private int buyAmount;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader inputData = new BufferedReader(new FileReader(fromFileName));
            processInputData(inputData);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading input file: " + e.getMessage(), e);
        }

        try {
            writeToFile(toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing output file: " + e.getMessage(), e);
        }
    }

    private void processInputData(BufferedReader inputData) throws IOException {
        supplyAmount = 0;
        buyAmount = 0;
        String line;
        while ((line = inputData.readLine()) != null) {
            String[] values = line.split(",");
            String operationType = values[OPERATION_INDEX];
            int amount = Integer.parseInt(values[AMOUNT_INDEX]);
            if ("supply".equals(operationType)) {
                supplyAmount += amount;
            } else if ("buy".equals(operationType)) {
                buyAmount += amount;
            }
        }
    }

    private void writeToFile(String toFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAmount + System.lineSeparator());
            writer.write("buy," + buyAmount + System.lineSeparator());
            writer.write("result," + (supplyAmount - buyAmount) + System.lineSeparator());
        }
    }
}
