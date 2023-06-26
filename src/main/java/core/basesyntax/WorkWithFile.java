package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String actionType = line.split(COMMA)[0];
                int amount = Integer.parseInt(line.split(COMMA)[1]);

                if (actionType.equals(SUPPLY)) {
                    supplyAmount += amount;
                }

                if (actionType.equals(BUY)) {
                    buyAmount += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find file", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writeResult(supplyAmount, buyAmount, toFileName);
    }

    private void writeResult(int supplyAmount, int buyAmount, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + COMMA + supplyAmount + System.lineSeparator());
            bufferedWriter.write(BUY + COMMA + buyAmount + System.lineSeparator());
            bufferedWriter.write("result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
