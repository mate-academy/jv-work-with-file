package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String REPORT_HEADER_SUPPLY = "supply";
    private static final String REPORT_HEADER_BUY = "buy";
    private static final String REPORT_HEADER_RESULT = "result";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            String fileContent = readFile(fromFileName);
            String reportContent = generateReport(fileContent);
            writeToFile(toFileName, reportContent);
            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while processing the file: "
                    + e.getMessage(), e);
        }
    }

    private String readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(LINE_SEPARATOR);
            }
            return contentBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while reading the file: "
                    + e.getMessage(), e);
        }
    }

    private String generateReport(String fileContent) {
        int supplyTotal = processFileContent(fileContent, OPERATION_SUPPLY);
        int buyTotal = processFileContent(fileContent, OPERATION_BUY);
        int result = supplyTotal - buyTotal;
        return prepareReportContent(supplyTotal, buyTotal, result);
    }

    private void writeToFile(String fileName, String reportContent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reportContent);
        }
    }

    private int processFileContent(String fileContent, String operationType) {
        int total = 0;
        String[] lines = fileContent.split(LINE_SEPARATOR);
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length == 2) {
                String operation = parts[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
                if (operation.equals(operationType)) {
                    total += amount;
                }
            }
        }
        return total;
    }

    private String prepareReportContent(int supplyTotal, int buyTotal, int result) {
        String reportContent = REPORT_HEADER_SUPPLY + DELIMITER + supplyTotal + LINE_SEPARATOR
                + REPORT_HEADER_BUY + DELIMITER + buyTotal + LINE_SEPARATOR
                + REPORT_HEADER_RESULT + DELIMITER + result;
        return reportContent;
    }
}
