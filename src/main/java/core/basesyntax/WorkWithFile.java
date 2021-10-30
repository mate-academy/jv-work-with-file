package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION_TYPE = "supply";
    private static final String BUY_OPERATION_TYPE = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try {
            String[] stringsFromFile = Files.readString(
                    Path.of(fromFileName)).split(System.lineSeparator());
            for (String line : stringsFromFile) {
                String operationType = line.split(COMMA)[0];
                int operationSum = Integer.parseInt(line.split(COMMA)[1]);
                if (SUPPLY_OPERATION_TYPE.equals(operationType)) {
                    supplySum += operationSum;
                }
                if (BUY_OPERATION_TYPE.equals(operationType)) {
                    buySum += operationSum;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + fromFileName, e);
        }
        StringBuilder resultToWrite = new StringBuilder();
        resultToWrite.append(SUPPLY_OPERATION_TYPE).append(COMMA).append(supplySum)
                .append(System.lineSeparator()).append(BUY_OPERATION_TYPE).append(COMMA)
                .append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append((supplySum - buySum));
        try {
            Files.write(
                    Path.of(toFileName),
                    resultToWrite.toString().getBytes(),
                    StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName, e);
        }
    }
}
