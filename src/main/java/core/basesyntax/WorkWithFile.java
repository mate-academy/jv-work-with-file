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
    private static final int OPERATION_POSITION = 0;
    private static final int QUANTITY_POSITION = 1;
    private static final String COMMA = ",";
    private static final String SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder inputData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                inputData.append(value).append(SEPARATOR);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }
        String result = createReport(inputData);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }

    private String createReport(StringBuilder inputData) {
        StringBuilder reportBuilder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;
        String[] operationList = inputData.toString().split(SEPARATOR);
        for (String currentOperation : operationList) {
            String[] operationName = currentOperation.split(COMMA);
            int quantity = Integer.parseInt(operationName[QUANTITY_POSITION]);
            if (operationName[OPERATION_POSITION].equals(SUPPLY)) {
                totalSupply = totalSupply + quantity;
            }
            if (operationName[OPERATION_POSITION].equals(BUY)) {
                totalBuy = totalBuy + quantity;
            }
        }
        reportBuilder.append(SUPPLY).append(COMMA).append(totalSupply).append(SEPARATOR).append(BUY)
                .append(COMMA).append(totalBuy).append(SEPARATOR).append(RESULT).append(COMMA)
                .append(totalSupply - totalBuy);
        return reportBuilder.toString();
    }
}
