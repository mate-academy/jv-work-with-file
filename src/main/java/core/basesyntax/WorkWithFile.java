package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_AMOUNT = "supply";
    private static final String BUY_AMOUNT = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String arrayInfo = readFile(fromFileName);
        String reportData = getReport(arrayInfo);
        writeToFile(toFileName, reportData);
    }

    private String getReport(String inputData) {
        String[] inputInfo = inputData.split(System.lineSeparator());
        int sumSupply = 0;
        int sumBuy = 0;
        for (String separateInformation : inputInfo) {
            String[] eachValue = separateInformation.split(SEPARATOR);
            if (eachValue[INDEX_TYPE].equals(SUPPLY_AMOUNT)) {
                sumSupply += Integer.parseInt(eachValue[INDEX_AMOUNT]);
            }
            if (eachValue[INDEX_TYPE].equals(BUY_AMOUNT)) {
                sumBuy += Integer.parseInt(eachValue[INDEX_AMOUNT]);
            }
        }
        int differenceOfValues = sumSupply - sumBuy;
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY_AMOUNT).append(SEPARATOR)
                .append(sumSupply).append(System.lineSeparator())
                .append(BUY_AMOUNT).append(SEPARATOR).append(sumBuy).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(differenceOfValues).toString();
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read" + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file" + toFileName, e);
        }
    }
}
