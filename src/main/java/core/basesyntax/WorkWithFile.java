package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int TWO_FIELDS = 2;
    private static final String KOMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readLinesFromFile(fromFileName);
        int supplyTotal = 0;
        int buyTotal = 0;
        int supplyCount = 0;
        int buyCount = 0;
        for (String line : lines) {
            String[] fields = parseLine(line);
            String type = fields[0];
            int amount = Integer.parseInt(fields[1]);
            if (type.equals("supply")) {
                supplyTotal += amount;
                supplyCount++;
            } else if (type.equals("buy")) {
                buyTotal += amount;
                buyCount++;
            } else {
                throw new RuntimeException("Invalid input type: " + type);
            }
        }
        int result = calculateResult(supplyTotal, buyTotal);
        String report = generateReport(supplyTotal, buyTotal, result);
        writeReportToFile(toFileName, report);
    }

    private String[] readLinesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can not read from the file");
        }
    }

    private String[] parseLine(String line) {
        String[] fields = line.split(KOMA);
        if (fields.length != TWO_FIELDS) {
            throw new RuntimeException("Invalid input format");
        }
        return fields;
    }

    private int calculateResult(int supplyTotal, int buyTotal) {
        return supplyTotal - buyTotal;
    }

    private String generateReport(int supplyTotal, int buyTotal, int result) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator())
                .append("buy,").append(buyTotal).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private void writeReportToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to the file");
        }
    }
}
