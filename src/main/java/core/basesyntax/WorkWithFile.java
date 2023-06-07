package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String REPORT_HEADER_SUPPLY = "supply";
    private static final String REPORT_HEADER_BUY = "buy";
    private static final String REPORT_HEADER_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            List<String> fileContent = readFileContent(reader);

            int supplyTotal = processFileContent(fileContent, OPERATION_SUPPLY);
            int buyTotal = processFileContent(fileContent, OPERATION_BUY);
            int result = supplyTotal - buyTotal;

            String reportContent = prepareReportContent(supplyTotal, buyTotal, result);
            writeToFile(writer, reportContent);

            System.out.println("Report generated successfully.");

        } catch (IOException e) {
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }

    private List<String> readFileContent(BufferedReader reader) throws IOException {
        List<String> fileContent = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            fileContent.add(line);
        }
        return fileContent;
    }

    private int processFileContent(List<String> fileContent, String operationType) {
        int total = 0;
        for (String line : fileContent) {
            String[] parts = line.split(DELIMITER);
            if (parts.length == 2) {
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());
                if (operation.equals(operationType)) {
                    total += amount;
                }
            }
        }
        return total;
    }

    private String prepareReportContent(int supplyTotal, int buyTotal, int result) {
        String reportContent = REPORT_HEADER_SUPPLY + DELIMITER + supplyTotal + "\r\n"
                + REPORT_HEADER_BUY + DELIMITER + buyTotal + "\r\n"
                + REPORT_HEADER_RESULT + DELIMITER + result;
        return reportContent;
    }

    private void writeToFile(BufferedWriter writer, String content) throws IOException {
        writer.write(content);
    }
}
