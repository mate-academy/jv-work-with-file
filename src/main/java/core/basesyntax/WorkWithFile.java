package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int OPERATION_VALUE_INDEX = 1;
    public static final String SUPPLY_OPERATION_NAME = "supply";
    public static final String BUY_OPERATION_NAME = "buy";
    public static final String RESULT_LABEL_VALUE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] statistics;
        try {
            statistics = readStatisticsFromFile(fromFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





        try {
            writeReportToFile(toFileName, report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file", e);
        }

    }

    private String[] readStatisticsFromFile(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName)).toArray(new String[0]);
    }

    private void writeReportToFile(String fileName, String report) throws IOException {
        Files.writeString(Path.of(fileName), report);
    }

    private int[] calculateStatistics(String[] statistics) {
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String operation : statistics) {
            String operationType = operation.split(",")[OPERATION_TYPE_INDEX];
            int operationValue = Integer.parseInt(operation.split(",")[OPERATION_VALUE_INDEX]);

            if (operationType.equals(SUPPLY_OPERATION_NAME)) {
                supplyAmount += operationValue;
            } else if (operationType.equals(BUY_OPERATION_NAME)) {
                buyAmount += operationValue;
            }
        }
        return new int[] { supplyAmount, buyAmount, (supplyAmount - buyAmount) };
    }

    private String createReport(int supply, int buy, int result) {
        return SUPPLY_OPERATION_NAME + "," + supply +
                System.lineSeparator() +
                BUY_OPERATION_NAME + "," + buy +
                System.lineSeparator() +
                RESULT_LABEL_VALUE + "," + (result);
    }

}
