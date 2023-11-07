package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supplyTotal = 0;
    private int buyTotal = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fileContent = readFileContent(fromFileName);
            String report = generateReport(fileContent);
            writeToFile(toFileName, report);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private String readFileContent(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private String generateReport(String fileContent) {
        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String operationType = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(operationType)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operationType)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;

        return "supply,"
                + supplyTotal
                + "\r\n"
                + "buy,"
                + buyTotal
                + "\r\n"
                + "result,"
                + result;
    }

    private void writeToFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }
}
