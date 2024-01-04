package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY_TYPE = "supply";
    private static final String BUY_TYPE = "buy";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = readData(fromFileName, SUPPLY_TYPE);
        int buyCount = readData(fromFileName, BUY_TYPE);
        int result = supplyCount - buyCount;
        writeReport(toFileName, supplyCount, buyCount, result);
    }

    private int readData(String fileName, String operationType) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return processData(reader, operationType);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
    }

    private void writeReport(String toFileName, int supplyCount, int buyCount, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY_TYPE + "," + supplyCount);
            writer.newLine();
            writer.write(BUY_TYPE + "," + buyCount);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private int processData(BufferedReader reader, String operationType) throws IOException {
        int count = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[OPERATION_TYPE_INDEX].equals(operationType)) {
                count += Integer.parseInt(parts[AMOUNT_INDEX]);
            }
        }
        return count;
    }
}
