package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String NAME_OF_SUPPLY = "supply";
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String toWrite;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            int supplyAmount = 0;
            int buyAmount = 0;
            while (value != null) {
                String[] values = value.split("[,[\\s]]");
                switch (values[OPERATION_INDEX]) {
                    case NAME_OF_SUPPLY:
                        supplyAmount += Integer.parseInt(values[AMOUNT_INDEX]);
                        break;
                    default:
                        buyAmount += Integer.parseInt(values[AMOUNT_INDEX]);
                        break;
                }
                value = bufferedReader.readLine();
            }
            int result = supplyAmount - buyAmount;
            toWrite = String.format("supply,%d%n"
                    + "buy,%d%n"
                    + "result,%d%n", supplyAmount, buyAmount, result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file" + fromFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(toWrite);
        } catch (IOException d) {
            throw new RuntimeException("Cannot write to the file" + toFileName, d);
        }
    }
}
