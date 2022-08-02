package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String LINE_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supplyAmount = 0;
            int buyAmount = 0;
            String line = bufferedReader.readLine();
            String[] split;

            while (line != null) {
                split = line.split(LINE_SEPARATOR);
                switch (split[FIRST_INDEX]) {
                    case "supply":
                        supplyAmount += Integer.parseInt(split[AMOUNT_INDEX]);
                        break;
                    case "buy":
                        buyAmount += Integer.parseInt(split[AMOUNT_INDEX]);
                        break;
                    default:
                        break;
                }
                line = bufferedReader.readLine();
            }
            bufferedWriter.write("supply," + supplyAmount + System.lineSeparator()
                    + "buy," + buyAmount + System.lineSeparator()
                    + "result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Is not possible to find a file.", e);
        }
    }
}
