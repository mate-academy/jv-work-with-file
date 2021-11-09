package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String FILE_SEPARATOR = ",";
    private static final String OPERATION_SUPPLY_NAME = "supply";
    private static final String OPERATION_BUY_NAME = "buy";
    private static final String OPERATION_RESULT_NAME = "result";

    private List<String> getAllOperationsFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file : " + e.toString());
        }
    }

    private void writeReportToFile(String report, String toFileName) {
        try {
            Files.writeString(Paths.get(toFileName),report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e.toString());
        }
    }

    private int calculateSupplyOperationsCount(List<String> operations) {
        int supplyOperationsCount = 0;
        for (String operation : operations) {
            String[] operationInfo = operation.split(FILE_SEPARATOR);
            String operationName = operationInfo[0];
            if (operationName.equals(OPERATION_SUPPLY_NAME)) {
                supplyOperationsCount += Integer.parseInt(operationInfo[1]);
            }
        }
        return supplyOperationsCount;
    }

    private int calculateBuyOperationsCount(List<String> operations) {
        int buyOperationsCount = 0;
        for (String operation : operations) {
            String[] operationInfo = operation.split(FILE_SEPARATOR);
            String operationName = operationInfo[0];
            if (operationName.equals(OPERATION_BUY_NAME)) {
                buyOperationsCount += Integer.parseInt(operationInfo[1]);
            }
        }
        return buyOperationsCount;
    }

    private int calculateOperationsResult(int supplyAmount, int buyAmount) {
        return supplyAmount - buyAmount;
    }

    private String createReport(String fromFileName) {
        List<String> allOperations = getAllOperationsFromFile(fromFileName);
        int supplyAmount = calculateSupplyOperationsCount(allOperations);
        int buyAmount = calculateBuyOperationsCount(allOperations);
        int operationsAmountResult = calculateOperationsResult(supplyAmount,buyAmount);
        return OPERATION_SUPPLY_NAME + FILE_SEPARATOR + supplyAmount + System.lineSeparator()
                + OPERATION_BUY_NAME + FILE_SEPARATOR + buyAmount + System.lineSeparator()
                + OPERATION_RESULT_NAME + FILE_SEPARATOR + operationsAmountResult;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(fromFileName);
        writeReportToFile(report, toFileName);
    }
}
