package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(toFileName, report);
    }

    private String readFileContent(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private String generateReport(String fileContent) {
        int supplyTotal = 0;
        int buyTotal = 0;

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

        return "supply," + supplyTotal
                + System.lineSeparator()
                + "buy,"
                + buyTotal
                + System.lineSeparator()
                + "result,"
                + result;
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
