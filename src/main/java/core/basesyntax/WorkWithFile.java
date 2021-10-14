package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String supplyOperationType = "supply";
        String buyOperationType = "buy";
        String result = "result";
        String comma = ",";
        int supplySum = 0;
        int buySum = 0;
        try {
            String[] stringsFromFile = Files.readString(
                    Path.of(fromFileName)).split(System.lineSeparator());
            for (String line : stringsFromFile) {
                String operationType = line.split(comma)[0];
                int operationSumma = Integer.parseInt(line.split(comma)[1]);
                if (supplyOperationType.equals(operationType)) {
                    supplySum += operationSumma;
                }
                if (buyOperationType.equals(operationType)) {
                    buySum += operationSumma;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + fromFileName, e);
        }
        String resultToWrite = supplyOperationType + comma + supplySum + System.lineSeparator()
                + buyOperationType + comma + buySum + System.lineSeparator()
                + result + comma + (supplySum - buySum);
        try {
            Files.write(
                    Path.of(toFileName), resultToWrite.getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName, e);
        }

    }
}
