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
    public static final String READ_ERROR_MESSAGE = "Error while reading from file";
    public static final String WRITE_ERROR_MESSAGE = "Error while writing to file";
    public static final String SEPARATOR = ",";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeAllContents(toFileName, getReport(readAllContents(fromFileName)));
    }

    private String readAllContents(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE, e);
        }
        return stringBuilder.toString();
    }

    private void writeAllContents(String toFileName, String output) {
        try (DataOutputStream outStream = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(toFileName)))) {
            outStream.writeUTF(output);
        } catch (IOException e) {
            throw new RuntimeException(WRITE_ERROR_MESSAGE, e);
        }
    }

    private String getReport(String input) {
        int supplySum = 0;
        int buySum = 0;
        int result;
        String[] fileContentArray = input.split(System.lineSeparator());

        for (String line : fileContentArray) {
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
        return SUPPLY + "," + supplySum + System.lineSeparator()
                + BUY + "," + buySum + System.lineSeparator()
                + RESULT + "," + result;
    }
}
