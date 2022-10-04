package core.basesyntax;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String ERROR_MESSAGE = "Error while working with file";
    public static final String SEPARATOR = ",";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        int result;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                DataOutputStream outStream = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(toFileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split(SEPARATOR);
                if (lineArray.length == 2) {
                    if (lineArray[OPERATION_INDEX].equals(SUPPLY)) {
                        supplySum += Integer.parseInt(lineArray[AMOUNT_INDEX]);
                    } else if (lineArray[OPERATION_INDEX].equals(BUY)) {
                        buySum += Integer.parseInt(lineArray[AMOUNT_INDEX]);
                    }
                }
            }
            result = supplySum - buySum;
            String output = SUPPLY + "," + supplySum + System.lineSeparator()
                    + BUY + "," + buySum + System.lineSeparator()
                    + RESULT + "," + result;

            outStream.writeUTF(output);
        } catch (IOException e) {
            throw new RuntimeException(ERROR_MESSAGE, e);
        }
    }
}
