package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supplyAmount = 0;
            int buyAmount = 0;
            String line;
            String[] split;
            do {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                split = line.split(",");
                switch (split[0]) {
                    case "supply":
                        supplyAmount += Integer.parseInt(split[1]);
                        break;
                    case "buy":
                        buyAmount += Integer.parseInt(split[1]);
                        break;
                    default:
                        break;
                }
            } while (line != null);
            bufferedWriter.write("supply," + supplyAmount + System.lineSeparator()
                    + "buy," + buyAmount + System.lineSeparator()
                    + "result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException("Is not possible to find a file.");
        }
    }
}
