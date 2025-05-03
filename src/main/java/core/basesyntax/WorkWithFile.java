package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String data = readFile(fromFileName);
            String report = generateReport(data);
            writeToFile(toFileName, report);
        } catch (IOException e) {
            System.out.println("An error occurred while reading/writing the file: "
                    + e.getMessage());
        }
    }

    private String readFile(String fromFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    private String generateReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);
                if (operationType.equals(OPERATION_SUPPLY)) {
                    supplyTotal += amount;
                } else if (operationType.equals(OPERATION_BUY)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;

        return "supply," + supplyTotal + "\n"
                + "buy," + buyTotal + "\n"
                + "result," + result + "\n";
    }

    private void writeToFile(String toFileName, String report) throws IOException {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(report);
        }
    }
}
