package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error reading from the file", e);
        }
    }

    private String createReport(String data) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] values = line.split(",");
            String operationType = values[OPERATION_TYPE_INDEX].trim();
            int amount = Integer.parseInt(values[AMOUNT_INDEX].trim());

            if (operationType.equals("supply")) {
                supplyTotal += amount;
            } else if (operationType.equals("buy")) {
                buyTotal += amount;
            }
        }

        int result = supplyTotal - buyTotal;

        return generateReportString(supplyTotal, buyTotal, result);
    }

    private String generateReportString(int supplyTotal, int buyTotal, int result) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplyTotal).append(System.lineSeparator());
        builder.append("buy,").append(buyTotal).append(System.lineSeparator());
        builder.append("result,").append(result);
        return builder.toString();
    }

    private void writeToFile(String report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file");
        }
    }
}
