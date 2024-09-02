package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_AMOUNT_INDEX = 1;
    private static final String BUY_OPERATION = "buy";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String SPLIT_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = calculateTotalAmountOfOperation(fromFileName, BUY_OPERATION);
        int supplySum = calculateTotalAmountOfOperation(fromFileName, SUPPLY_OPERATION);
        int result = supplySum - buySum;

        try (BufferedWriter output = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder builder = new StringBuilder();
            builder.append("supply").append(",").append(supplySum).append(System.lineSeparator())
                    .append("buy").append(",").append(buySum).append(System.lineSeparator())
                    .append("result").append(",").append(result);
            output.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName + e);
        }
    }

    private int calculateTotalAmountOfOperation(String fromFileName, String option) {
        if (!option.equals(BUY_OPERATION) && !option.equals(SUPPLY_OPERATION)) {
            throw new WrongOptionException("Wrong option");
        }

        try (BufferedReader input = new BufferedReader(new FileReader(fromFileName))) {
            String value = input.readLine();
            int operationSum = 0;
            while (value != null) {
                String[] valueSplit = value.split(SPLIT_REGEX);
                if (option.equals(SUPPLY_OPERATION) && valueSplit[OPERATION_TYPE_INDEX]
                        .equals(SUPPLY_OPERATION)) {
                    operationSum += Integer.parseInt(valueSplit[OPERATION_AMOUNT_INDEX]);
                } else if (option.equals(BUY_OPERATION) && valueSplit[OPERATION_TYPE_INDEX]
                        .equals(BUY_OPERATION)) {
                    operationSum += Integer.parseInt(valueSplit[OPERATION_AMOUNT_INDEX]);
                }
                value = input.readLine();
            }
            return operationSum;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + fromFileName + e);
        }
    }
}
