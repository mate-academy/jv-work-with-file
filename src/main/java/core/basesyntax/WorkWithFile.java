package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_FOR_OPERATION_TYPE = 0;
    private static final int INDEX_FOR_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyNumber = 0;
        int buyNumber = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] stringLine = value.split(",");
                if (stringLine[INDEX_FOR_OPERATION_TYPE].equals(SUPPLY)) {
                    supplyNumber += Integer.parseInt(stringLine[INDEX_FOR_AMOUNT]);
                }
                if (stringLine[INDEX_FOR_OPERATION_TYPE].equals(BUY)) {
                    buyNumber += Integer.parseInt(stringLine[INDEX_FOR_AMOUNT]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can`t read");
        }
        String output = SUPPLY + "," + supplyNumber + System.lineSeparator() + BUY + ","
                + buyNumber + System.lineSeparator() + RESULT + "," + (supplyNumber - buyNumber);
    }

    private void writeToWile(String output, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException("File can`t write");
        }
    }
}
