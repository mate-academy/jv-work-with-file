package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = ",";
    private static final int INDEX_NAME_OPERATION = 0;
    private static final int INDEX_AMOUNT = 1;
    private static final String NAME_ADD_PRODUCT = "supply";
    private static final String NAME_SUBTRACT_PRODUCT = "buy";
    private static final String NAME_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName);
        String statistic = calculateStatistic(lines);
        writeStatistic(toFileName, statistic);
    }

    private String calculateStatistic(String[] lines) {
        int supplyProduct = 0;
        int buyProduct = 0;
        for (String data : lines) {
            String operation = data.split(LINE_SEPARATOR)[INDEX_NAME_OPERATION];
            int amount = Integer.valueOf(data.split(LINE_SEPARATOR)[INDEX_AMOUNT].trim());
            if (operation.equals(NAME_ADD_PRODUCT)) {
                supplyProduct += amount;
            } else if (operation.equals(NAME_SUBTRACT_PRODUCT)) {
                buyProduct += amount;
            }
        }
        return formatStatistic(supplyProduct, buyProduct);
    }

    private String[] readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String formatStatistic(int supplyProduct, int buyProduct) {
        StringBuilder builder = new StringBuilder();
        return builder.append(NAME_ADD_PRODUCT).append(LINE_SEPARATOR).append(supplyProduct)
                .append(System.lineSeparator()).append(NAME_SUBTRACT_PRODUCT).append(LINE_SEPARATOR)
                .append(buyProduct).append(System.lineSeparator()).append(NAME_RESULT)
                .append(LINE_SEPARATOR).append(supplyProduct - buyProduct).toString();
    }

    private void writeStatistic(String fileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {

            writer.write(statistic);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}
