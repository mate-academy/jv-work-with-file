package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_VALUE_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = calculateReport(data);
        writeIntoFile(toFileName,report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return data.toString().split(System.lineSeparator());
    }

    private String calculateReport(String[] data) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String reports : data) {
            String[] recortSplit = reports.split(SEPARATOR);
            String operationType = recortSplit[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(recortSplit[OPERATION_VALUE_INDEX]);
            if (SUPPLY.equals(operationType)) {
                totalSupply += amount;
            } else if (BUY.equals(operationType)) {
                totalBuy += amount;
            }
        }
        int result = totalSupply - totalBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(SEPARATOR).append(totalSupply).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(totalBuy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(totalSupply - totalBuy);
        return builder.toString();
    }

    private void writeIntoFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write data " + toFileName, e);
        }
    }
}
