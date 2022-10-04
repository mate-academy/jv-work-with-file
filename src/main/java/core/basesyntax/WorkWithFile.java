package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT_LOG_STRING = "result";
    private static final String ROW_SEPARATOR = ",";
    private static final int DATA_INDEX_OPERATION_TYPE = 0;
    private static final int DATA_INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supply = 0;
            int buy = 0;
            for (String row : Files.readAllLines(new File(fromFileName).toPath())) {
                String[] rowData = row.split(ROW_SEPARATOR);
                int amount = Integer.parseInt(rowData[DATA_INDEX_AMOUNT]);
                if (OPERATION_SUPPLY.equals(rowData[DATA_INDEX_OPERATION_TYPE])) {
                    supply += amount;
                } else {
                    buy += amount;
                }
            }
            bufferedWriter.write(
                    OPERATION_SUPPLY + ROW_SEPARATOR + supply
                            + System.lineSeparator() + OPERATION_BUY + ROW_SEPARATOR + buy
                            + System.lineSeparator() + RESULT_LOG_STRING + ROW_SEPARATOR + (supply - buy)
            );
        } catch (IOException e) {
            throw new RuntimeException("Cannot perform this operation", e);
        }
    }
}
