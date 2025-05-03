package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUP_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = calculateReport(readFromFile(fromFileName));
        writeReport(toFileName, report);
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file: " + fileName, e);
        }
        return data.toString();
    }

    private String calculateReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals(SUP_OPERATION)) {
                    supplyTotal += amount;
                } else if (operation.equals(BUY_OPERATION)) {
                    buyTotal += amount;
                }
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator());
        reportBuilder.append("buy,").append(buyTotal).append(System.lineSeparator());
        reportBuilder.append("result,").append(supplyTotal - buyTotal);
        return reportBuilder.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report: " + toFileName, e);
        }
    }
}
