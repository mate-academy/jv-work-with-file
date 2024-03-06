package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder rawData = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rawData.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return rawData.toString();
    }

    private int parseAmount(String rawData, String operationType) {
        int amount = 0;
        String[] lines = rawData.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(operationType)) {
                amount += Integer.parseInt(parts[1]);
            }
        }
        return amount;
    }

    private String generateReport(String rawData) {
        int supplyAmount = parseAmount(rawData, "supply");
        int buyAmount = parseAmount(rawData, "buy");
        int resultAmount = supplyAmount - buyAmount;
        return "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + resultAmount;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
