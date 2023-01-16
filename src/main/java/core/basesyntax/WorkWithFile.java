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
        List<String> inLines;
        int supply = 0;
        int buy = 0;

        try {
            inLines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }

        for (String line : inLines) {
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
            }
        }

        try (BufferedWriter reportWriter = Files.newBufferedWriter(Path.of(toFileName))) {
            reportWriter.write(INCOME_INVOICE_STRING + CSV_DATA_DELIMITER + supply);
            reportWriter.newLine();
            reportWriter.write(OUTCOME_INVOICE_STRING + CSV_DATA_DELIMITER + buy);
            reportWriter.newLine();
            reportWriter.write(TOTAL_SUPPLY_STRING + CSV_DATA_DELIMITER + (supply - buy));
            reportWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fromFileName, e);
        }
    }
}
