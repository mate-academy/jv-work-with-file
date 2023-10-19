package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        int[] supplyAndBuy = infoProcess(dataFromFile);
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

    private int[] infoProcess(String[] dataFromFile) {
        int sumSupply = 0;
        int sumBuy = 0;

        for (String line : dataFromFile) {
            String[] split = line.split(COMMA);
            String operation = split[FIRST_PART];
            int digit = Integer.parseInt(split[SECOND_PART]);

            if (operation.equals(SUPPLY)) {
                sumSupply += digit;
            } else {
                sumBuy += digit;
            }
        }
        return new int[]{sumSupply, sumBuy};
    }

    private String createReport(int[] supplyAndBuy) {
        int supply = supplyAndBuy[FIRST_PART];
        int buy = supplyAndBuy[SECOND_PART];
        int result = supply - buy;

        return new StringBuilder()
                .append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator())
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
        System.arraycopy(data, FIRST_PART, dataArray, FIRST_PART, data.length);
        dataArray[data.length] = line;
        return dataArray;
    }
}
