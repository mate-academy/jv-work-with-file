package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private final String FILE_SEPARATOR = ",";
    private final String OPERATION_SUPPLY_NAME = "supply";
    private final String OPERATION_BUY_NAME = "buy";
    private final String OPERATION_RESULT_NAME = "result";
    private List<String> allOperations;
    private int supplyAmount;
    private int buyAmount;
    private int operationsAmountResult;
    private String report;
    private String fromFileName;
    private String toFileName;

    private List<String> getAllOperationsFromFile() {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file : " + e.toString());
        }
    }

    private void writeReportToFile() {
        try {
            Files.writeString(Paths.get(toFileName),report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + e.toString());
        }
    }

    private void calculateOperationByName(String  operationName, int operationAmount) {
        switch (operationName) {
            case OPERATION_SUPPLY_NAME:
                supplyAmount += operationAmount;
                break;
            case OPERATION_BUY_NAME:
                buyAmount += operationAmount;
        }
    }

    private void calculateOperationsResult() {
        operationsAmountResult = supplyAmount - buyAmount;
    }

    private void calculateOperationsAmount() {
        for (String operation : allOperations) {
            String[] operationInfo = operation.split(FILE_SEPARATOR);
            String operationName = operationInfo[0];
            int operationAmount = Integer.parseInt(operationInfo[1]);
            calculateOperationByName(operationName,operationAmount);
        }
        calculateOperationsResult();
    }

    private void createReport() {
        calculateOperationsAmount();
        report = OPERATION_SUPPLY_NAME + FILE_SEPARATOR + supplyAmount + System.lineSeparator() +
                OPERATION_BUY_NAME + FILE_SEPARATOR + buyAmount + System.lineSeparator() +
                OPERATION_RESULT_NAME + FILE_SEPARATOR + operationsAmountResult;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        this.fromFileName = fromFileName;
        this.toFileName = toFileName;
        this.allOperations = getAllOperationsFromFile();
        createReport();
        writeReportToFile();
    }
}
