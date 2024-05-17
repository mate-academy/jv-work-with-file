package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SIGN_COMMA = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final int AMOUNT_DATA_IN_LINE = 2;
    private static final int OPERATION_COLUMN_INDEX = 0;
    private static final int AMOUNT_COLUMN_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] inputData = readDataFromFile(fromFileName);
        String report = createReport(inputData);
        writeDataToFile(toFileName, report);
    }

    private String[][] readDataFromFile(String fileName) {
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file", e);
        }

        return getTwoDemensionalArray(stringBuilder.toString());
    }

    private void writeDataToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.append(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String[][] getTwoDemensionalArray(String string) {
        String[] strings = string.split(NEW_LINE);
        String[][] data = new String[strings.length][AMOUNT_DATA_IN_LINE];

        for (int i = 0; i < strings.length; i++) {
            data[i] = strings[i].split(SIGN_COMMA);
        }

        return data;
    }

    private String createReport(String[][] inputData) {
        int supplyAmount = 0;
        int buyAmount = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < inputData.length; i++) {
            if (inputData[i][OPERATION_COLUMN_INDEX].equals(SUPPLY_OPERATION)) {
                supplyAmount += Integer.parseInt(inputData[i][AMOUNT_COLUMN_INDEX]);
            } else if (inputData[i][OPERATION_COLUMN_INDEX].equals(BUY_OPERATION)) {
                buyAmount += Integer.parseInt(inputData[i][AMOUNT_COLUMN_INDEX]);
            }
        }

        stringBuilder
                .append(SUPPLY_OPERATION).append(SIGN_COMMA).append(supplyAmount).append(NEW_LINE)
                .append(BUY_OPERATION).append(SIGN_COMMA).append(buyAmount).append(NEW_LINE)
                .append(RESULT_OPERATION).append(SIGN_COMMA).append(supplyAmount - buyAmount);

        return stringBuilder.toString();
    }
}
