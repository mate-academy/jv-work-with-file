package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String BUY = "buy";
    public static final String COMA = ",";
    public static final String SUPPLY = "supply";
    public static final String RESULT = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readFile(fromFileName));
        writeToFile(toFileName,report);
    }

    private String[] readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char)value);
                value = bufferedReader.read();
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException exception) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, exception);
        }
    }

    private String createReport(String[] data) {
        int buyCount = 0;
        int supplyCount = 0;
        for (String line : data) {
            if (line.split(COMA)[OPERATION_INDEX].equals(BUY)) {
                buyCount += Integer.parseInt(line.split(COMA)[AMOUNT_INDEX]);
            } else {
                supplyCount += Integer.parseInt(line.split(COMA)[AMOUNT_INDEX]);
            }
        }
        int result = supplyCount - buyCount;
        return (new StringBuilder(SUPPLY).append(COMA).append(supplyCount)
                .append(System.lineSeparator()).append(BUY).append(COMA)
                .append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(COMA).append(result)).toString();
    }

    private void writeToFile(String toFileName, String data) {
        try (FileWriter toFile = new FileWriter(toFileName)) {
            toFile.write(data);
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file" + toFileName, exception);
        }
    }
}
