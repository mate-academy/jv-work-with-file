package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int DATA_INDEX = 1;
    private static final String SUPPLY_LINE = "supply,";
    private static final String BUY_LINE = "buy,";
    private static final String RESULT_LINE = "result,";

    private int supplySum = 0;
    private int buySum = 0;
    private int result = 0;

    public void readData(String fileNameToRead) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileNameToRead))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    public void processLine(String line) {
        String[] parts = line.split(",");

        if (parts[OPERATION_TYPE_INDEX].equals(SUPPLY_OPERATION)) {
            supplySum += Integer.parseInt(parts[DATA_INDEX]);
        } else {
            buySum += Integer.parseInt(parts[DATA_INDEX]);
        }
    }

    public void writeData(String fileNameToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameToWrite))) {
            writer.write(SUPPLY_LINE + supplySum + System.lineSeparator());
            writer.write(BUY_LINE + buySum + System.lineSeparator());
            result = supplySum - buySum;
            writer.write(RESULT_LINE + result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write the file", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readData(fromFileName);
        writeData(toFileName);
        supplySum = 0;
        buySum = 0;
    }
}
