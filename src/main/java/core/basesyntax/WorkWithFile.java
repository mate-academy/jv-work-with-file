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
    private StringBuilder stringBuilder = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        String[][] inputData = readDataFromFile(fromFileName);

        for (int i = 0; i < inputData.length; i++) {
            if (inputData[i][0].equals(SUPPLY_OPERATION)) {
                supplyAmount += Integer.parseInt(inputData[i][1]);
            } else if (inputData[i][0].equals(BUY_OPERATION)) {
                buyAmount += Integer.parseInt(inputData[i][1]);
            }
        }

        stringBuilder.setLength(0);
        stringBuilder
                .append(SUPPLY_OPERATION).append(SIGN_COMMA).append(supplyAmount).append(NEW_LINE)
                .append(BUY_OPERATION).append(SIGN_COMMA).append(buyAmount).append(NEW_LINE)
                .append(RESULT_OPERATION).append(SIGN_COMMA).append(supplyAmount - buyAmount);

        writeDataToFile(toFileName, stringBuilder.toString());
    }

    private String[][] readDataFromFile(String fileName) {
        String line = null;

        stringBuilder.setLength(0);
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
            writer.append(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

    private String[][] getTwoDemensionalArray(String string) {
        String[] strings = string.split(NEW_LINE);
        String[][] data = new String[strings.length][2];

        for (int i = 0; i < strings.length; i++) {
            data[i] = strings[i].split(SIGN_COMMA);
        }
        return data;
    }
}
