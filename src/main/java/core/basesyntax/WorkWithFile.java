package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUMMA_SUPPLY_INDEX = 1;
    private static final int SUMMA_BUY_INDEX = 1;
    private static final int ARRAY_INDEX = 0;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] substring = value.split(",");
                if (substring[ARRAY_INDEX].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(substring[SUMMA_SUPPLY_INDEX]);
                }
                if (substring[ARRAY_INDEX].equals(BUY)) {
                    buySum += Integer.parseInt(substring[SUMMA_BUY_INDEX]);
                }
                value = reader.readLine();
            }
            result = supplySum - buySum;
            builder.append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator())
                    .append(BUY).append(",").append(buySum).append(System.lineSeparator())
                    .append("result").append(",").append(result);
        } catch (IOException e) {
            throw new RuntimeException("File can't read", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("File can't write", e);
        }
    }
}
