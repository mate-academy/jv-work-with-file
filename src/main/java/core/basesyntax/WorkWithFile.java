package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String SUPPLY_OPERATION_TYPE = "supply";
        String BUY_OPERATION_TYPE = "buy";
        String RESULT = "result";
        String COMMA = ",";
        int supplySum = 0;
        int buySum = 0;
        try {
            String[] stringsFromFile = Files.readString(Path.of(fromFileName)).split(System.lineSeparator());
            for (String line : stringsFromFile) {
                String operationType = line.split(COMMA)[0];
                int operationSumma = Integer.parseInt(line.split(COMMA)[1]);
                if (SUPPLY_OPERATION_TYPE.equals(operationType)) {
                    supplySum += operationSumma;
                }
                if (BUY_OPERATION_TYPE.equals(operationType)) {
                    buySum += operationSumma;
                }
            }
            String resultToWrite = SUPPLY_OPERATION_TYPE + COMMA + supplySum + System.lineSeparator()
                    + BUY_OPERATION_TYPE + COMMA + buySum + System.lineSeparator()
                    + RESULT + COMMA + (supplySum - buySum);
            Files.deleteIfExists(Path.of(toFileName));
            Files.write(Path.of(toFileName), resultToWrite.getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file: " + fromFileName, e);
        }

    }
}
