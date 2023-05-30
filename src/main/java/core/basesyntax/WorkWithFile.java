package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final String FILE_SEPARATOR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] fromFileLineArray;
        String fromFileLine;
        int supplySum = 0;
        int buySum = 0;
        int result;
        try (BufferedReader buffer = new BufferedReader(new FileReader(fromFileName))) {
            fromFileLine = buffer.readLine();
            while (fromFileLine != null) {
                fromFileLineArray = fromFileLine.split(FILE_SEPARATOR);
                if (fromFileLineArray[OPERATION_INDEX].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(fromFileLineArray[VALUE_INDEX]);
                }
                if (fromFileLineArray[OPERATION_INDEX].equals(BUY)) {
                    buySum += Integer.parseInt(fromFileLineArray[VALUE_INDEX]);
                }
                fromFileLine = buffer.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        result = supplySum - buySum;
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferWriter.write(SUPPLY + FILE_SEPARATOR + supplySum + System.lineSeparator());
            bufferWriter.write(BUY + FILE_SEPARATOR + buySum + System.lineSeparator());
            bufferWriter.write(RESULT + FILE_SEPARATOR + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
