package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> operations = new HashMap<>();

        readAndCalculateOperations(operations, fromFileName);
        writeCalculationResults(operations, toFileName);
    }

    private void readAndCalculateOperations(HashMap<String, Integer> operations, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                String[] values = line.split(",");
                String operation = values[OPERATION_INDEX];
                int amount = Integer.parseInt(values[AMOUNT_INDEX]);

                int total = operations.getOrDefault(operation, 0) + amount;
                operations.put(operation, total);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File '" + fileName + "' not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file '" + fileName + "'.", e);
        }
    }

    private void writeCalculationResults(HashMap<String, Integer> operations, String fileName) {
        int supply = operations.getOrDefault(SUPPLY_OPERATION, 0);
        int buy = operations.getOrDefault(BUY_OPERATION, 0);
        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(SUPPLY_OPERATION + "," + supply + System.lineSeparator());
            writer.write(BUY_OPERATION + "," + buy + System.lineSeparator());
            writer.write(OPERATION_RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file '" + fileName + "'.", e);
        }
    }
}
