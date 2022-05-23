package core.basesyntax;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReaderSupplier {
    public static final String SUPPLY_VAR = "supply";
    public static final String BUY_VAR = "buy";

    public static final String RESULT_VAR = "result";

    public String getFileContent(String fromFileName) {
        int buySum = 0;
        int supplySum = 0;
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(fromFileName));
            StringBuilder builder = new StringBuilder();
            String value;
            while ((value = reader.readLine()) != null) {
                String[] valuesArray = value.split(",");
                if (valuesArray[0].equals(BUY_VAR)) {
                    buySum += Integer.parseInt(valuesArray[1]);
                }
                if (valuesArray[0].equals(SUPPLY_VAR)) {
                    supplySum += Integer.parseInt(valuesArray[1]);
                }
            }
            builder.append(SUPPLY_VAR).append(",").append(supplySum).append(System.lineSeparator())
                    .append(BUY_VAR).append(",").append(buySum).append(System.lineSeparator())
                    .append(RESULT_VAR).append(",").append(supplySum - buySum);
            return builder.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
