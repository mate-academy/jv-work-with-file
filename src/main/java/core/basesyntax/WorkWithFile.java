package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file", e);
        }
        return data.toString();
    }

    private String createReport(String dataFromFile) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = dataFromFile.split("\n");
        for (String line : lines) {
            String[] wordsAndValues = line.split(",");
            String operationType = wordsAndValues[0];
            int amount = Integer.parseInt(wordsAndValues[1]);
            if ("supply".equals(operationType)) {
                supplyTotal += amount;
            } else if ("buy".equals(operationType)) {
                buyTotal += amount;
            }
        }
        int result = supplyTotal - buyTotal;
        return "supply," + supplyTotal + "\nbuy," + buyTotal + "\nresult," + result;
    }

    private void writeReportToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error writing report to file", e);
        }
    }
}

