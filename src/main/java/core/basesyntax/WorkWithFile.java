package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int ZERO_INDEX = 0;
    private static final int FIRST_INDEX = 1;
    private static final String[] SUPPLY_AND_BUY = new String[]{"supply", "buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        String report = getReport(lines);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fromFileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return lines;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private String getReport(List<String> lines) {
        StringBuilder report = new StringBuilder();
        int quantityOfSupplies = 0;
        int quantityOfBuys = 0;
        for (String supplyAndBuy : SUPPLY_AND_BUY) {
            Integer quantity = 0;
            for (String line : lines) {
                String[] split = line.split(",");
                if (supplyAndBuy.equals(split[ZERO_INDEX])) {
                    quantity += Integer.parseInt(split[FIRST_INDEX]);
                }
            }
            if (supplyAndBuy.equals(SUPPLY_AND_BUY[ZERO_INDEX])) {
                quantityOfSupplies = quantity;
            } else {
                quantityOfBuys = quantity;
            }
            report.append(supplyAndBuy).append(",").append(quantity).append(System.lineSeparator());
        }
        report.append("result,").append(quantityOfSupplies - quantityOfBuys);
        return report.toString();
    }
}
