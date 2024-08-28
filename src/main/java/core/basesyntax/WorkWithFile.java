package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final String INPUT_FILE_NAME = "input.csv";
    private static final String OUTPUT_FILE_NAME = "output.csv";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = calculate(fromFileName, SUPPLY);
        int totalBuy = calculate(fromFileName, BUY);
        int result = totalSupply - totalBuy;
        writeStatisticsToFile(toFileName, totalSupply, totalBuy, result);
    }

    public void getStatistic() {
        getStatistic(INPUT_FILE_NAME, OUTPUT_FILE_NAME);
    }

    private int calculate(String fileName, String operationType) {
        int total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                total += parseAndSum(line, operationType);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fileName, e);
        }
        return total;
    }

    private int parseAndSum(String line, String operationType) {
        String[] parts = line.split(SEPARATOR);
        if (parts.length == 2) {
            String operation = parts[0];
            if (operation.equals(operationType)) {
                try {
                    return Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Wrong format " + line, e);
                }
            }
        }
        return 0;
    }

    private void writeStatisticsToFile(String fileName, int supplyTotal, int buyTotal, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(generateReport(supplyTotal, buyTotal, result));
        } catch (IOException e) {
            throw new RuntimeException("Can't save data in file " + fileName, e);
        }
    }

    private String generateReport(int supplyTotal, int buyTotal, int result) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supplyTotal).append(System.lineSeparator());
        report.append(BUY).append(SEPARATOR).append(buyTotal).append(System.lineSeparator());
        report.append(RESULT).append(SEPARATOR).append(result).append(System.lineSeparator());
        return report.toString();
    }
}
