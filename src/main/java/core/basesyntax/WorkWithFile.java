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
            throw new RuntimeException("File has not found" + file.getName(), e);
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
        int[] totalAmount = calculateTotalAmount(data);
        int supplySum = totalAmount[0];
        int buySum = totalAmount[1];
        return builder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(supplySum - buySum).toString();
    }

    private int[] calculateTotalAmount(String data) {
        String[] splitData = data.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String item : splitData) {
            String[] itemInfo = item.split(COMMA_SEPARATOR);
            if (itemInfo[ITEM_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(itemInfo[COST_INDEX]);
            } else {
                buySum += Integer.parseInt(itemInfo[COST_INDEX]);
            }
        }
        return new int[]{supplySum, buySum};
    }
}
