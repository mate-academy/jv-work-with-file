package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }
        return fileContent.toString();
    }

    private String generateReport(String fileContent) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] data = line.split(SEPARATOR);
            String operation = data[OPERATION_INDEX].trim();
            int amount = Integer.parseInt(data[AMOUNT_INDEX].trim());

            if (operation.equalsIgnoreCase("supply")) {
                supplyTotal += amount;
            } else if (operation.equalsIgnoreCase("buy")) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;
        return "supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file: " + toFileName, e);
        }
    }
}
