package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String RESULT_OPERATION = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;

    private static final String VALUES_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFromFile(fromFileName);
        String report = generateReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder fileRead = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String fileReadLine = bufferedReader.readLine();
            while (fileReadLine != null) {
                fileRead.append(fileReadLine).append(System.lineSeparator());
                fileReadLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + fromFileName + " not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("I/O operation interrupted.", e);
        }
        return String.valueOf(fileRead);
    }

    private String generateReport(String fileContent) {
        int supplyOperationSum = 0;
        int buyOperationSum = 0;
        String[] fileContentSplit = fileContent.split(System.lineSeparator());
        for (String fileContentLine : fileContentSplit) {
            String[] fileContentLineSplit = fileContentLine.split(VALUES_SEPARATOR);
            String operationType = fileContentLineSplit[OPERATION_INDEX];
            String operationAmountString = fileContentLineSplit[OPERATION_AMOUNT_INDEX];
            int operationAmount = 0;
            try {
                operationAmount = Integer.parseInt(operationAmountString);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Wrong operation amount format.", e);
            }
            switch (operationType) {
                case BUY_OPERATION: {
                    buyOperationSum += operationAmount;
                    break;
                }
                case SUPPLY_OPERATION: {
                    supplyOperationSum += operationAmount;
                    break;
                }
                default: {
                    throw new RuntimeException("Wrong operation type.");
                }
            }
        }
        return SUPPLY_OPERATION + VALUES_SEPARATOR + supplyOperationSum + System.lineSeparator()
                + BUY_OPERATION + VALUES_SEPARATOR + buyOperationSum + System.lineSeparator()
                + RESULT_OPERATION + VALUES_SEPARATOR + (supplyOperationSum - buyOperationSum);
    }

    private static void writeToFile(String report, String toFile) {
        File resultFile = new File(toFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile))) {
            String[] reportSplit = report.split(System.lineSeparator());
            for (String reportSplitLine : reportSplit) {
                bufferedWriter.write(reportSplitLine);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("I/O operation interrupted", e);
        }
    }
}
