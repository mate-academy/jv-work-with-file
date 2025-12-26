package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String value = bufferedReader.readLine();
            int supplySum = 0;
            int buySum = 0;

            while (value != null) {
                String[] words = value.split(",");
                String name = words[INDEX_ZERO];
                int num = Integer.parseInt(words[INDEX_ONE]);
                if (name.equals(SUPPLY)) {
                    supplySum += num;
                } else {
                    buySum += num;
                }
                value = bufferedReader.readLine();
            }
            int result = supplySum - buySum;
            bufferedWriter.write(
                    SUPPLY + "," + supplySum + System.lineSeparator()
                    + BUY + "," + buySum + System.lineSeparator()
                    + RESULT + "," + result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found",e);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong",e);
        }
    }
}
