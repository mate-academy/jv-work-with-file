package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final char SEPARATOR = ',';
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int nextIndex = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            int supplySum = 0;
            int buySum = 0;
            while (value != null) {
                int sum = Integer.parseInt(value.substring(value.indexOf(SEPARATOR) + nextIndex));
                if (value.contains(SUPPLY)) {
                    supplySum += sum;
                }
                if (value.contains(BUY)) {
                    buySum += sum;
                }
                value = bufferedReader.readLine();
            }
            int resultAmount = supplySum - buySum;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(SUPPLY + SEPARATOR + supplySum
                        + System.lineSeparator() + BUY + SEPARATOR + buySum
                        + System.lineSeparator() + RESULT + SEPARATOR + resultAmount);
            } catch (IOException j) {
                throw new RuntimeException("Can't write file " + toFileName, j);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
    }
}
