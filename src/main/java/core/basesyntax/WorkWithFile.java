package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(SEPARATOR);

                String operation = data[0];
                int amount = Integer.parseInt(data[1]);

                if (SUPPLY_OPERATION.equals(operation)) {
                    totalSupply += amount;
                } else if (BUY_OPERATION.equals(operation)) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        int result = totalSupply - totalBuy;

        String report = SUPPLY_OPERATION + SEPARATOR + totalSupply
                + System.lineSeparator()
                + BUY_OPERATION + SEPARATOR + totalBuy
                + System.lineSeparator()
                + "result" + SEPARATOR + result;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }

}
