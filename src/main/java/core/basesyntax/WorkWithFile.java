package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
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
        int quantityOfSupplies = 0;
        int quantityOfBuys = 0;
        for (String line : lines) {
            String[] split = line.split(",");
            String typeOfOperation = split[0];
            int quantity = Integer.parseInt(split[1]);
            if (typeOfOperation.equals("supply")) {
                quantityOfSupplies += quantity;
            } else {
                quantityOfBuys += quantity;
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(quantityOfSupplies).append(System.lineSeparator());
        report.append("buy,").append(quantityOfBuys).append(System.lineSeparator());
        report.append("result,").append(quantityOfSupplies - quantityOfBuys);
        return report.toString();
    }
}
