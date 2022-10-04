package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String ROW_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supply = 0;
            int buy = 0;
            for (String row : Files.readAllLines(new File(fromFileName).toPath())) {
                String[] rowData = row.split(ROW_SEPARATOR);
                int amount = Integer.parseInt(rowData[1]);
                if (OPERATION_SUPPLY.equals(rowData[0])) {
                    supply += amount;
                } else {
                    buy += amount;
                }
            }
            bufferedWriter.write(
                    OPERATION_SUPPLY + ROW_SEPARATOR + supply
                            + System.lineSeparator() + OPERATION_BUY + ROW_SEPARATOR + buy
                            + System.lineSeparator() + "result" + ROW_SEPARATOR + (supply - buy)
            );
        } catch (IOException e) {
            throw new RuntimeException("Cannot perform this operation", e);
        }
    }
}
