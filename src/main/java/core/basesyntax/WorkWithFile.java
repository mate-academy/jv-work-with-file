package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final int INDEX_SUPPLY = 0;
    public static final int INDEX_BUY = 1;
    public static final String OPERATION_BUY = "buy";
    public static final String OPERATION_SUPPLY = "supply";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFromFile(fromFileName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(OPERATION_SUPPLY).append(",").append(data[INDEX_SUPPLY])
                    .append(System.lineSeparator())
                    .append(OPERATION_BUY).append(",").append(data[INDEX_BUY])
                    .append(System.lineSeparator())
                    .append(RESULT).append(",").append(data[INDEX_SUPPLY] - data[INDEX_BUY]);
        writeToFile(stringBuffer.toString(), toFileName);
    }

    private int[] readFromFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        File file = new File(fromFileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row = bufferedReader.readLine();
            while (row != null) {
                String[] data = row.split(",");
                if (data[OPERATION_INDEX].equals(OPERATION_SUPPLY)) {
                    supply += Integer.parseInt(data[AMOUNT_INDEX]);
                } else {
                    buy += Integer.parseInt(data[AMOUNT_INDEX]);
                }
                row = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return new int[]{supply, buy};
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + toFileName, e);
        }
    }
}
