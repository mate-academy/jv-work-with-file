package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final String READ_ERROR_MSG = "Error: file not found: ";
    private static final String WRITE_ERROR_MSG = "Cant write data to file: ";

    public void getStatistic(String fromFileName, String toFileName) {
        int buyQuantity = 0;
        int supplyQuantity = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                if (BUY.equals(value.replaceAll("\\W\\d+", ""))) {
                    buyQuantity += Integer.parseInt(value.replaceAll("\\D", ""));
                }
                if (SUPPLY.equals(value.replaceAll("\\W\\d+", ""))) {
                    supplyQuantity += Integer.parseInt(value.replaceAll("\\D", ""));
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MSG, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String result = stringBuilder.append(SUPPLY).append(COMMA).append(supplyQuantity)
                .append(System.lineSeparator()).append(BUY).append(COMMA).append(buyQuantity)
                .append(System.lineSeparator()).append(RESULT).append(COMMA)
                .append(supplyQuantity - buyQuantity).toString();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(WRITE_ERROR_MSG, e);
        }
    }
}
