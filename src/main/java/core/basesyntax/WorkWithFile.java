package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    private static final String GAP = " ";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append(GAP);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
        return builder.toString();
    }

    private String generateReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = data.split(GAP);
        StringBuilder reportBuilder = new StringBuilder();

        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length != 2) {
                System.out.println("Invalid data format: " + line);
                continue;
            }
            String operationType = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operationType.equalsIgnoreCase(SUPPLY)) {
                supplyTotal += amount;
            } else if (operationType.equalsIgnoreCase(BUY)) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;

        reportBuilder.append(SUPPLY).append(COMMA).append(supplyTotal)
                .append(System.lineSeparator());
        reportBuilder.append(BUY).append(COMMA).append(buyTotal).append(System.lineSeparator());
        reportBuilder.append(RESULT).append(COMMA).append(result);

        return reportBuilder.toString();
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
