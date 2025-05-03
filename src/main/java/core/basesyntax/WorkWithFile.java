package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFromFile(fromFileName);
        String report = createReport(fileData);
        writeReportIntoFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        final StringBuilder stringBuilder = new StringBuilder();

        String fileLine;

        try (final BufferedReader bufferedReader
                 = new BufferedReader(new FileReader(fromFileName))) {
            while ((fileLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(fileLine);
                stringBuilder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        return stringBuilder.toString();
    }

    private String createReport(String inputData) {
        int totalSupplyAmount = 0;
        int totalBuyAmount = 0;

        String[] data = inputData.split(System.lineSeparator());

        for (String line : data) {
            String[] lineData = line.split(DELIMITER);
            String operationType = lineData[OPERATION_TYPE_INDEX];
            int operationAmount = Integer.parseInt(lineData[OPERATION_AMOUNT_INDEX]);

            if (operationType.equals(SUPPLY_STRING)) {
                totalSupplyAmount += operationAmount;
            } else if (operationType.equals(BUY_STRING)) {
                totalBuyAmount += operationAmount;
            }
        }

        final int result = totalSupplyAmount - totalBuyAmount;

        return SUPPLY_STRING
            + DELIMITER
            + totalSupplyAmount
            + System.lineSeparator()
            + BUY_STRING
            + DELIMITER
            + totalBuyAmount
            + System.lineSeparator()
            + RESULT_STRING
            + DELIMITER
            + result;
    }

    public void writeReportIntoFile(String toFileName, String report) {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into the file " + toFileName, e);
        }
    }
}
