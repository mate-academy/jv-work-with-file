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
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        int[] supplyAndBuy = getSumAndBuy(dataFromFile);
        String report = createReport(supplyAndBuy);
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String[] data = new String[0];
            while ((line = reader.readLine()) != null) {
                data = append(data, line);
            }
            return data;
        } catch (IOException exception) {
            throw new RuntimeException("Can`t read data from file " + fromFileName, exception);
        }
    }

    private int[] getSumAndBuy(String[] dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;

        for (String line : dataFromFile) {
            String[] split = line.split(COMMA);
            String operation = split[OPERATION_TYPE_INDEX];
            int digit = Integer.parseInt(split[AMOUNT_INDEX]);

            if (operation.equals(SUPPLY)) {
                sumSupply += digit;
            } else {
                sumBuy += digit;
            }
        }
        return new int[]{sumSupply, sumBuy};
    }

    private String createReport(int[] supplyAndBuy) {
        int supply = supplyAndBuy[0];
        int buy = supplyAndBuy[1];
        int result = supply - buy;

        return new StringBuilder()
                .append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(result).append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException exception) {
            throw new RuntimeException("Can`t write data to file " + toFileName, exception);
        }
    }

    private String[] append(String[] data, String line) {
        String[] dataArray = new String[data.length + 1];
        System.arraycopy(data, 0, dataArray, 0, data.length);
        dataArray[data.length] = line;
        return dataArray;
    }
}
