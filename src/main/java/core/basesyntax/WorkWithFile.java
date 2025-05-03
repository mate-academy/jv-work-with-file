package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File inputFile = new File(fromFileName);
        File outputFile = new File(toFileName);

        String fileContent = readFile(inputFile);
        String report = processContent(fileContent);
        writeFile(outputFile, report);
    }

    private String readFile(File inputFile) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + inputFile.getName(), e);
        }
        return contentBuilder.toString().trim();
    }

    private String processContent(String fileContent) {
        String[] lines = fileContent.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid line format: " + line);
            }

            String operation = parts[0];
            int amount;
            try {
                amount = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid amount format in line: " + line, e);
            }

            if (SUPPLY.equals(operation)) {
                supplyAmount += amount;
            } else if (BUY.equals(operation)) {
                buyAmount += amount;
            } else {
                throw new IllegalArgumentException("Unknown operation type: " + operation);
            }
        }

        int resultAmount = supplyAmount - buyAmount;
        return new StringBuilder()
                .append(SUPPLY).append(",").append(supplyAmount).append(System.lineSeparator())
                .append(BUY).append(",").append(buyAmount).append(System.lineSeparator())
                .append(RESULT).append(",").append(resultAmount)
                .toString();
    }

    private void writeFile(File outputFile, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + outputFile.getName(), e);
        }
    }
}