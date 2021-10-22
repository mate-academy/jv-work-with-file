package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String supplyOperationType = "supply";
        final String buyOperationType = "buy";
        final String result = "result";
        final String comma = ",";
        int supplySum = 0;
        int buySum = 0;
        try {
            String[] stringsFromFile = Files.readString(
                    Path.of(fromFileName)).split(System.lineSeparator());
            for (String line : stringsFromFile) {
                String operationType = line.split(comma)[0];
                int operationSum = Integer.parseInt(line.split(comma)[1]);
                if (supplyOperationType.equals(operationType)) {
                    supplySum += operationSum;
                }
                if (buyOperationType.equals(operationType)) {
                    buySum += operationSum;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + fromFileName, e);
        }
        StringBuilder resultToWrite = new StringBuilder();
        resultToWrite.append(supplyOperationType).append(comma).append(supplySum)
                .append(System.lineSeparator()).append(buyOperationType).append(comma)
                .append(buySum).append(System.lineSeparator())
                .append(result).append(comma).append((supplySum - buySum));
        try {
            Files.write(
                    Path.of(toFileName), resultToWrite.toString().getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName, e);
        }
    }
}
