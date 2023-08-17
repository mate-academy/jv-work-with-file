package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = calculateReport(readFromFile(fromFileName));
        writeToFile(toFileName, report);
        System.out.println("Report generated successfully.");
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file:" + fileName, e);
        }
        return data.toString();
    }

    private String calculateReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] operationInfo = line.split(COMMA);
            String operationType = operationInfo[0];
            int operationAmount = Integer.parseInt(operationInfo[1]);
            if (operationType.equalsIgnoreCase(SUPPLY)) {
                supplyTotal += operationAmount;
            } else {
                buyTotal += operationAmount;
            }
        }
        int result = supplyTotal - buyTotal;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(COMMA).append(supplyTotal).append(System.lineSeparator());
        report.append(BUY).append(COMMA).append(buyTotal).append(System.lineSeparator());
        report.append(RESULT).append(COMMA).append(result);

        return report.toString();
    }

    private void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write in file:" + fileName, e);
        }
    }
}

