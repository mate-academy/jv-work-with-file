package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT = "result";
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        String report = createReport(data);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private String createReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String el : data) {
            String[] partOfData = el.split(SEPARATOR);
            int amount = Integer.parseInt(partOfData[INDEX_OF_AMOUNT]);
            String operationType = partOfData[INDEX_OF_OPERATION_TYPE];
            if (operationType.equals(BUY_OPERATION)) {
                buyAmount += amount;
                continue;
            }
            supplyAmount += amount;
        }
        int result = supplyAmount - buyAmount;
        report.append(SUPPLY_OPERATION).append(SEPARATOR).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(SEPARATOR).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result);
        return report.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file " + toFileName, e);
        }
    }
}
