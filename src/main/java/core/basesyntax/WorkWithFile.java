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

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        int result;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                DataOutputStream outStream = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(toFileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArray = line.split(",");
                if (lineArray.length == 2) {
                    if (lineArray[0].equals(SUPPLY)) {
                        supplySum += Integer.parseInt(lineArray[1]);
                    } else if (lineArray[0].equals(BUY)) {
                        buySum += Integer.parseInt(lineArray[1]);
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
