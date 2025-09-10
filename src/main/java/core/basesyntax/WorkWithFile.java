package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INITIAL_VALUE = 0;
    private static final int OPERATION_PART = 0;
    private static final int TRANSACTION_AMOUNT_PART = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static class Aggregates {
        private int totalSupply = INITIAL_VALUE;
        private int totalBuy = INITIAL_VALUE;

        public void addSupply(int amount) {
            this.totalSupply += amount;
        }

        public void addBuy(int amount) {
            this.totalBuy += amount;
        }

        public int getTotalSupply() {
            return totalSupply;
        }

        public int getTotalBuy() {
            return totalBuy;
        }
    }

    public String getStatistic(String fromFileName, String toFileName) {
        Aggregates aggregates = aggregateFromFile(fromFileName);
        String report = buildReport(aggregates);
        writeReport(report, toFileName);
        return report;
    }

    private Aggregates aggregateFromFile(String fileName) {
        Aggregates aggregates = new Aggregates();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String csvData;

            while ((csvData = bufferedReader.readLine()) != null) {
                String[] dataParts = csvData.split(SEPARATOR);

                if (dataParts.length != 2) {
                    throw new RuntimeException(
                            "Malformed CSV line in file: " + fileName + "line: " + csvData);
                }

                String operation = dataParts[OPERATION_PART].trim();
                String amountToken = dataParts[TRANSACTION_AMOUNT_PART].trim();

                int transactionAmount;
                try {
                    transactionAmount = Integer.parseInt(amountToken);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "Can`t parse amount in file: " + fileName + "line: " + csvData, e);
                }

                if (OPERATION_SUPPLY.equals(operation)) {
                    aggregates.addSupply(transactionAmount);
                } else if (OPERATION_BUY.equals(operation)) {
                    aggregates.addBuy(transactionAmount);
                } else {
                    throw new RuntimeException(
                            "Unknown operation in file: " + fileName + "line: " + csvData);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file: " + fileName, e);
        }
        return aggregates;
    }

    private String buildReport(Aggregates aggregates) {
        int result = aggregates.getTotalSupply() - aggregates.getTotalBuy();
        return new StringBuilder()
                .append(OPERATION_SUPPLY).append(SEPARATOR)
                .append(aggregates.totalSupply).append(LINE_SEPARATOR)
                .append(OPERATION_BUY).append(SEPARATOR)
                .append(aggregates.totalBuy).append(LINE_SEPARATOR)
                .append(OPERATION_RESULT).append(SEPARATOR).append(result)
                .toString();
    }

    private void writeReport(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Can`t write data to file " + toFileName, e);
        }
    }
}
