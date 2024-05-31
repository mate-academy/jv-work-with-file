package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException exception) {
            throw new RuntimeException("Can't read file.", exception);
        }
        return content.toString().split(System.lineSeparator());
    }

    private String createReport(String[] lines) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operation.equals("supply")) {
                totalSupply += amount;
            } else if (operation.equals("buy")) {
                totalBuy += amount;
            }
        }

        int result = totalSupply - totalBuy;

        return new StringBuilder()
                    .append("supply,").append(totalSupply).append(System.lineSeparator())
                    .append("buy,").append(totalBuy).append(System.lineSeparator())
                    .append("result,").append(result).append(System.lineSeparator())
                    .toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException exception) {
            throw new RuntimeException("Can't write to file.", exception);
        }
    }
}
