package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fileContent = readFromFile(fromFileName);
            String report = createReport(fileContent);
            writeToFile(report, toFileName);
        } catch (IOException e) {
            System.out.println("An error occurred while processing the file: " + e.getMessage());
        }
    }

    private String readFromFile(String fromFileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private String createReport(String fileContent) {
        int supplySum = 0;
        int buySum = 0;
        StringBuilder sb = new StringBuilder();
        String[] lines = fileContent.split("\n");
        for (String line : lines) {
            String[] splited = line.split(",");
            String operation = splited[0];
            int amount = Integer.parseInt(splited[1]);
            if (operation.equalsIgnoreCase("supply")) {
                supplySum += amount;
            } else if (operation.equalsIgnoreCase("buy")) {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;
        sb.append("supply,").append(supplySum).append("\n");
        sb.append("buy,").append(buySum).append("\n");
        sb.append("result,").append(result).append("\n");
        return sb.toString();
    }

    private void writeToFile(String report, String toFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        }
    }
}
