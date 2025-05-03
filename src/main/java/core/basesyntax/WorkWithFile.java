package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String splitChar = ",";
    private final String supplyString = "supply";
    private final String buyString = "buy";
    private final String resultString = "result";
    private int totalSupply = 0;
    private int totalBuy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        totalSupply = 0;
        totalBuy = 0;
        result = 0;
        String fileContent = readFile(fromFileName);
        calculateStatistics(fileContent);
        writeToFile(toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private void calculateStatistics(String fileContent) {
        final int partsCheckValue = 2;
        String[] lines = fileContent.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(splitChar);
            if (parts.length == partsCheckValue) {
                int amount = Integer.parseInt(parts[1].trim());
                if (parts[0].trim().equals(supplyString)) {
                    totalSupply += amount;
                } else if (parts[0].trim().equals(buyString)) {
                    totalBuy += amount;
                }
            }
        }
        result = totalSupply - totalBuy;
    }

    private void writeToFile(String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(supplyString)
                .append(splitChar)
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(buyString)
                .append(splitChar)
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(resultString)
                .append(splitChar)
                .append(result);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write in file: " + toFileName, e);
        }
    }
}
