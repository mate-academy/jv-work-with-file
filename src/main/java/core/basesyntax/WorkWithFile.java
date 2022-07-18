package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = ",";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFile(fromFileName);
        String statistic = calculateStatistic(lines);
        writeStatistic(toFileName, statistic);
    }

    private String calculateStatistic(String[] lines) {
        int supplyProduct = 0;
        int buyProduct = 0;
        for (String line : lines) {
            String operation = line.split(LINE_SEPARATOR)[INDEX_OF_OPERATION];
            int amount = Integer.valueOf(line.split(LINE_SEPARATOR)[INDEX_OF_AMOUNT].trim());

            if (operation.equals("supply")) {
                supplyProduct += amount;
            } else if (operation.equals("buy")) {
                buyProduct += amount;
            }
        }
        return buildStatistic(supplyProduct, buyProduct);
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

    private String buildStatistic(int supplyProduct, int buyProduct) {
        StringBuilder builder = new StringBuilder();
        return builder.append("supply").append(LINE_SEPARATOR).append(supplyProduct)
                .append(System.lineSeparator()).append("buy").append(LINE_SEPARATOR)
                .append(buyProduct).append(System.lineSeparator()).append("result")
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
