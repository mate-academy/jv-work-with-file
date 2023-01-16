package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_DATA_DELIMITER = ",";
    private static final String INCOME_INVOICE_STRING = "supply";
    private static final String OUTCOME_INVOICE_STRING = "buy";
    private static final String TOTAL_SUPPLY_STRING = "result";
    private static final int OPERATION_ID = 0;
    private static final int OPERATION_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> inLines = readFromFile(fromFileName);
        String report = createReport(inLines);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
        return lines;
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter reportWriter = Files.newBufferedWriter(Path.of(fileName))) {
            reportWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }

    private String createReport(List<String> dataLines) {
        StringBuilder reportBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;

        for (String line : dataLines) {
            String[] data = line.split(CSV_DATA_DELIMITER);
            switch (data[OPERATION_ID]) {
                case INCOME_INVOICE_STRING: {
                    supply += Integer.parseInt(data[OPERATION_AMOUNT]);
                    break;
                }
                case OUTCOME_INVOICE_STRING: {
                    buy += Integer.parseInt(data[OPERATION_AMOUNT]);
                    break;
                }
                default:
                    break;
            }
        }
        reportBuilder.append(INCOME_INVOICE_STRING + CSV_DATA_DELIMITER + supply)
                .append(System.lineSeparator())
                .append((OUTCOME_INVOICE_STRING + CSV_DATA_DELIMITER + buy))
                .append(System.lineSeparator())
                .append(TOTAL_SUPPLY_STRING + CSV_DATA_DELIMITER + (supply - buy))
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }
}
