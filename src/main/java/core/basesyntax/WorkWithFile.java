package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";
    private static final String SEPARATE = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] split;
        int supplyValue = 0;
        int buyValue = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value;
            while ((value = reader.readLine()) != null) {
                split = value.split(SEPARATE);
                if (split[OPERATION_INDEX].equals(OPERATION_SUPPLY)) {
                    supplyValue = supplyValue + Integer.parseInt(split[VALUE_INDEX]);
                } else {
                    buyValue = buyValue + Integer.parseInt(split[VALUE_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't not read file" + fromFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(OPERATION_SUPPLY + SEPARATE + supplyValue
                    + System.lineSeparator());
            bufferedWriter.write(OPERATION_BUY + SEPARATE + buyValue + System.lineSeparator());
            bufferedWriter.write(OPERATION_RESULT + SEPARATE + (supplyValue - buyValue));
        } catch (IOException e) {
            throw new RuntimeException("Can't not write data to file" + toFileName, e);
        }
    }
}
