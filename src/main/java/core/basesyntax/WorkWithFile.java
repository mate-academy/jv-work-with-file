package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public static final String lineSeparator = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileData = readFile(fromFileName);
        String report = makeReport(fileData);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fileName) {
        List<String> fileData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileData.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading data from the file " + fileName, e);
        }
        return fileData;
    }

    private String makeReport(List<String> fileData) {
        int supplyTotal = 0;
        int buyTotal = 0;
        for (String line : fileData) {
            String[] parts = line.split(",");
            String operationType = parts[0];
            int amount = Integer.parseInt(parts[1].trim());
            if (operationType.equals("supply")) {
                supplyTotal += amount;
            } else {
                buyTotal += amount;
            }
        }
        int result = calculateResult(supplyTotal, buyTotal);
        return "supply," + supplyTotal + lineSeparator
                + "buy," + buyTotal + lineSeparator
                + "result," + result;
    }

    private int calculateResult(int supplyTotal, int buyTotal) {
        return supplyTotal - buyTotal;
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report to the file " + fileName, e);
        }
    }
}
