package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileData = readFromFile(fromFileName);
        String report = generateReport(fileData);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas odczytu pliku: " + fileName, e);
        }
        return lines;
    }

    private String generateReport(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                continue;
            }
            String operationType = parts[0].trim();
            try {
                int amount = Integer.parseInt(parts[1].trim());
                if (SUPPLY.equals(operationType)) {
                    supplyTotal += amount;
                } else if (BUY.equals(operationType)) {
                    buyTotal += amount;
                }
            } catch (NumberFormatException e) {
                System.err.println("Błąd formatu liczby w linii: " + line);
            }
        }

        int result = supplyTotal - buyTotal;
        return new StringBuilder()
                .append(SUPPLY).append(",").append(supplyTotal).append(System.lineSeparator())
                .append(BUY).append(",").append(buyTotal).append(System.lineSeparator())
                .append("result,").append(result).append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu do pliku: " + fileName, e);
        }
    }
}
