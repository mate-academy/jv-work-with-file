package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SEPARATOR = ",";
    public static final String NEW_LINE = System.lineSeparator();
    public static final String RESULT_HEADER = "result";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fileContent = readFileContent(fromFileName);
            String report = formReport(fileContent);
            writeReportToFile(toFileName, report);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from or writing to file: "
                    + e.getMessage(), e);
        }
    }

    private String readFileContent(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(NEW_LINE);
            }
            return contentBuilder.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Error reading from file " + fileName + ": "
                    + e.getMessage(), e);
        }
    }

    private String formReport(String fileContent) {
        int supplyTotal = 0;
        int buyTotal = 0;
        StringBuilder reportBuilder = new StringBuilder();

        String[] lines = fileContent.split(NEW_LINE);
        for (String line : lines) {
            String[] parts = line.split(SEPARATOR);
            if (parts.length == 2) {
                String operation = parts[OPERATION_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                if ("supply".equals(operation)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operation)) {
                    buyTotal += amount;
                }
            }
        }

        int result = supplyTotal - buyTotal;
        reportBuilder.append("supply").append(SEPARATOR).append(supplyTotal).append(NEW_LINE)
                .append("buy").append(SEPARATOR).append(buyTotal).append(NEW_LINE)
                .append(RESULT_HEADER).append(SEPARATOR).append(result);
        return reportBuilder.toString();
    }

    private void writeReportToFile(String fileName, String report) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new IOException("Error writing to file " + fileName + ": " + e.getMessage(), e);
        }
    }
}
