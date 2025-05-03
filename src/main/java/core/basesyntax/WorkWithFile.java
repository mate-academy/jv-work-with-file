package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA_SYMBOL = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getData(fromFileName);
        String report = createReport(data);
        writeResultToFile(toFileName, report);
    }

    private String getData(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder builder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
    }

    private String createReport(String data) {
        StringBuilder value = new StringBuilder();
        int amountSupply = 0;
        int amountBuy = 0;
        String[] firstSplitFile = data.split(System.lineSeparator());
        for (String element : firstSplitFile) {
            String[] splitFile = element.split(COMMA_SYMBOL);
            if (splitFile[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                amountSupply += Integer.parseInt(splitFile[AMOUNT_INDEX]);
            }
            if (splitFile[OPERATION_TYPE_INDEX].equals(BUY)) {
                amountBuy += Integer.parseInt(splitFile[AMOUNT_INDEX]);
            }
        }
        int totalAmount = amountSupply - amountBuy;
        return value.append(SUPPLY).append(COMMA_SYMBOL)
                .append(amountSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA_SYMBOL)
                .append(amountBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA_SYMBOL)
                .append(totalAmount).toString();
    }

    private void writeResultToFile(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}
