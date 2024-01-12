package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = "\\s*,\\s*";
    private static final int TYPE = 0;
    private static final int SIZE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String data = readFile(fromFileName);
            String report = generateReport(data);
            writeToFile(toFileName, report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String fromFileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    private String generateReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            String operationType = parts[TYPE];
            int quantity = Integer.parseInt(parts[SIZE]);

            if ("supply".equals(operationType)) {
                totalSupply += quantity;
            } else if ("buy".equals(operationType)) {
                totalBuy += quantity;
            }
        }

        return String.format("supply,%d%nbuy,%d%nresult,%d%n",
                totalSupply, totalBuy, (totalSupply - totalBuy));
    }

    private void writeToFile(String toFileName, String report) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        }
    }
}
