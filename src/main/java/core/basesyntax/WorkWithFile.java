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
    private static final String SPACE = " ";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] data = readData(fromFileName);
        writeData(createReport(data), toFileName);
    }

    private String[] readData(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));) {
            String value = bufferedReader.readLine();
            while (value != null && !value.isEmpty()) {
                stringBuilder.append(value).append(SPACE);
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return stringBuilder.toString().split(SPACE);
    }

    private void writeData(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private String createReport(String[] data) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (String operation : data) {
            String[] array = operation.split(COMA);
            if (array[0].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(array[1]);
            } else if (array[0].equals(BUY)) {
                buyAmount += Integer.parseInt(array[1]);
            }
        }
        int result = supplyAmount - buyAmount;
        return stringBuilder.append(SUPPLY).append(COMA)
                .append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(COMA)
                .append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(COMA)
                .append(result).toString();
    }
}
