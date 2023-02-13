package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ITEM_INDEX = 0;
    private static final int COST_INDEX = 1;
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        writeToFile(toFileName, makeReport(data));
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File has not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + file.getName(), e);
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + file.getName(), e);
        }
    }

    private String makeReport(String data) {
        StringBuilder builder = new StringBuilder();
        int amountSupply = calculateTotalAmount("supply", data);
        int amountBuy = calculateTotalAmount("buy", data);
        return builder.append("supply,").append(amountSupply).append(System.lineSeparator())
                .append("buy,").append(amountBuy).append(System.lineSeparator())
                .append("result,").append(amountSupply - amountBuy).toString();
    }

    private int calculateTotalAmount(String value, String data) {
        String[] splitData = data.split(System.lineSeparator());
        int totalAmount = 0;
        for (String item : splitData) {
            String[] itemInfo = item.split(COMMA_SEPARATOR);
            if (itemInfo[ITEM_INDEX].equals(value)) {
                totalAmount += Integer.parseInt(itemInfo[COST_INDEX]);
            }
        }
        return totalAmount;
    }
}
