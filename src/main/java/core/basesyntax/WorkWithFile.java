package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SPLIT_BY = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = createReport(fileContent);
        writeFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
        return contentBuilder.toString();
    }

    private String createReport(String fileContent) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder reportBuilder = new StringBuilder();
        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] data = line.split(CSV_SPLIT_BY);
            String operation = data[0].trim();
            int amount = Integer.parseInt(data[1].trim());
            if (operation.equals("supply")) {
                supplyAmount += amount;
            } else if (operation.equals("buy")) {
                buyAmount += amount;
            }
        }
        int result = supplyAmount - buyAmount;
        reportBuilder.append("supply,").append(supplyAmount).append(System.lineSeparator());
        reportBuilder.append("buy,").append(buyAmount).append(System.lineSeparator());
        reportBuilder.append("result,").append(result).append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }
}
