package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String RESULT_LOG_STRING = "result";
    private static final String ROW_SEPARATOR = ",";
    private static final int DATA_INDEX_OPERATION_TYPE = 0;
    private static final int DATA_INDEX_AMOUNT = 1;
    private int supply = 0;
    private int buy = 0;
    private String fromFileName;

    public void getStatistic(String fromFileName, String toFileName) {
        this.fromFileName = fromFileName;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            countOperationsCost();
            writeToFile(bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException("Cannot perform this operation", e);
        }
    }

    private void countOperationsCost() throws IOException {
        for (String row : getFileLines()) {
            String[] rowData = row.split(ROW_SEPARATOR);
            int amount = Integer.parseInt(rowData[DATA_INDEX_AMOUNT]);
            if (OPERATION_SUPPLY.equals(rowData[DATA_INDEX_OPERATION_TYPE])) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
    }

    private List<String> getFileLines() throws IOException {
        return Files.readAllLines(new File(fromFileName).toPath());
    }

    private void writeToFile(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(getLogInfo());
    }

    private String getLogInfo() {
        return OPERATION_SUPPLY + ROW_SEPARATOR + supply
                + System.lineSeparator() + OPERATION_BUY + ROW_SEPARATOR + buy
                + System.lineSeparator() + RESULT_LOG_STRING + ROW_SEPARATOR + (supply - buy);
    }
}
