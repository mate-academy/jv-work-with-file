package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFileContent(fromFileName);
        String report = crateReport(fileContent);
        writeReportToFile(toFileName, report);
    }

    private String readFileContent(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data: " + fromFileName, e);
        }
        return content.toString();
    }

    private String crateReport(String fileContent) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder report = new StringBuilder();
        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1].trim());
            if (operation.equals("supply")) {
                supplySum += amount;
            } else if (operation.equals("buy")) {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;
        report.append("supply,").append(supplySum).append(System.lineSeparator());
        report.append("buy,").append(buySum).append(System.lineSeparator());
        report.append("result,").append(result);
        return report.toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data: " + toFileName, e);
        }
    }
}
