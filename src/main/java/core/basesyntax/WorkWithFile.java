package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String COMA_SEPARATOR = ",";
    private static final String BUY_OPERATION_NAME = "buy";
    private static final String SUPPLY_OPERATION_NAME = "supply";
    private static final String RESULT_OPERATION_NAME = "result";
    private static final String READING_FILE_EXCEPTION_MESSAGE = "Can't read the file - ";
    private static final String WRITING_FILE_EXCEPTION_MESSAGE = "Can't write data the file - ";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContents = readFile(fromFileName);
        String report = makeReport(fileContents);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(READING_FILE_EXCEPTION_MESSAGE + fileName, e);
        }
    }

    private String makeReport(String contents) {
        int totalSupply = calculateOperationTotalValue(SUPPLY_OPERATION_NAME, contents);
        int totalBuy = calculateOperationTotalValue(BUY_OPERATION_NAME, contents);
        int result = totalSupply - totalBuy;

        return SUPPLY_OPERATION_NAME + COMA_SEPARATOR + totalSupply + System.lineSeparator()
                + BUY_OPERATION_NAME + COMA_SEPARATOR + totalBuy + System.lineSeparator()
                + RESULT_OPERATION_NAME + COMA_SEPARATOR + result;
    }

    private int calculateOperationTotalValue(String operationName, String contents) {
        int operationTotal = 0;
        for (String content : contents.trim().split(System.lineSeparator())) {
            String[] line = content.split(COMA_SEPARATOR);
            String operation = line[OPERATION_INDEX];
            int value = Integer.parseInt(line[VALUE_INDEX]);
            if (operation.equals(operationName)) {
                operationTotal += value;
            }
        }

        return operationTotal;
    }

    private void writeReportToFile(String report, String fileName) {
        try {
            Files.write(Paths.get(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(WRITING_FILE_EXCEPTION_MESSAGE + fileName, e);
        }
    }
}
