package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<String> dataFromFile = readFile(fromFileName);
        int[] allOperations = processOperations(dataFromFile);
        String report = createReport(allOperations);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fromFileName) {
        List<String> operations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                operations.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }

        return operations;
    }

    private int[] processOperations(List<String> operations) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : operations) {
            String[] data = line.split(COMMA_CHAR);
            String operationType = data[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(data[AMOUNT_INDEX].trim());

            if (SUPPLY.equals(operationType)) {
                totalSupply += amount;
            } else if (BUY.equals(operationType)) {
                totalBuy += amount;
            }
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

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
