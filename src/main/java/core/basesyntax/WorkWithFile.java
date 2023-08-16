package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String report = calculateReport(readFromFile(fromFileName));
            writeToFile(toFileName, report);
            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(String fileName) throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        }
        return data.toString();
    }

    private String calculateReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length == 2) {
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());
                if (operationType.equalsIgnoreCase(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operationType.equalsIgnoreCase(BUY)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;

        StringBuilder report = new StringBuilder();
        report.append(SUPPLY + COMMA).append(supplyTotal).append(System.lineSeparator());
        report.append(BUY + COMMA).append(buyTotal).append(System.lineSeparator());
        report.append(RESULT + COMMA).append(result);

        return report.toString();
    }

    private void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }
}

