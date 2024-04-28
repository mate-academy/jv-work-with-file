package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int AMOUNT_INDEX = 1;
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int SUPPLY_DATA_INDEX = 0;
    public static final int BUY_DATA_INDEX = 1;
    public static final String COMMA_CHAR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReport(toFileName, report);
    }

    private int[] readFile(String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(COMMA_CHAR);
                String operationType = data[OPERATION_TYPE_INDEX].trim();
                int amount = Integer.parseInt(data[AMOUNT_INDEX].trim());

                if (SUPPLY.equals(operationType)) {
                    totalSupply += amount;
                } else if (BUY.equals(operationType)) {
                    totalBuy += amount;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
        return new int[]{totalSupply, totalBuy};
    }

    private String createReport(int[] data) {
        int totalSupply = data[SUPPLY_DATA_INDEX];
        int totalBuy = data[BUY_DATA_INDEX];
        int result = totalSupply - totalBuy;
        StringBuilder csvData = new StringBuilder(SUPPLY)
                .append(COMMA_CHAR)
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA_CHAR)
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA_CHAR)
                .append(result);
        return csvData.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
