package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int DATA_LENGTH = 2;
    private static final int INDEX_OPERATION_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String [] data = line.split(",");
                if (data.length != DATA_LENGTH) {
                    throw new RuntimeException("Invalid format line - [" + line + "]");
                }
                switch (data[INDEX_OPERATION_TYPE]) {
                    case SUPPLY:
                        supplySum += Integer.parseInt(data[INDEX_AMOUNT]);
                        break;
                    case BUY:
                        buySum += Integer.parseInt(data[INDEX_AMOUNT]);
                        break;
                    default:
                        throw new RuntimeException("Unknown type operation - ["
                                + data[INDEX_OPERATION_TYPE] + "]");
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        saveResultToFile(toFileName, supplySum, buySum);
    }

    private void saveResultToFile(String fileName, int supplySum, int buySum) {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(fileName))) {
            reader.write(SUPPLY + "," + supplySum + System.lineSeparator());
            reader.write(BUY + "," + buySum + System.lineSeparator());
            reader.write("result," + (supplySum - buySum) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file - [" + fileName + "]", e);
        }
    }
}
