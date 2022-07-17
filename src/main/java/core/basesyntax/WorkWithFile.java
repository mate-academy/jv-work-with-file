package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataProduct = readFile(fromFileName);
        String statistic = calcStatistic(dataProduct);
        writeStatistic(toFileName, statistic);
    }

    private String calcStatistic(String[] dataProduct) {
        int supplyProduct = 0;
        int buyProduct = 0;
        for (String data : dataProduct) {
            String operation = extractOperation(data);
            int amount = extractAmount(data);
            if (operation.equals("supply")) {
                supplyProduct += amount;
            } else if (operation.equals("buy")) {
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
            throw new RuntimeException("Can't open file " + fileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String formatStatistic(int supplyProduct, int buyProduct) {
        StringBuilder builder = new StringBuilder();
        return builder.append("supply").append(CSV_SEPARATOR).append(supplyProduct)
                .append(System.lineSeparator()).append("buy").append(CSV_SEPARATOR)
                .append(buyProduct).append(System.lineSeparator()).append("result")
                .append(CSV_SEPARATOR).append(supplyProduct - buyProduct).toString();
    }

    private String extractOperation(String dataProduct) {
        return dataProduct.split(CSV_SEPARATOR)[0];
    }

    private int extractAmount(String dataProduct) {
        return Integer.valueOf(dataProduct.split(CSV_SEPARATOR)[1].trim());
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
