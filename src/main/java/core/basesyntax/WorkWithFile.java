package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String BUY = "buy";
    static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String value = reader.readLine();
            String[] strings;
            int supplyResult = 0;
            int buyResult = 0;
            while (value != null) {
                strings = value.split(",");
                if (strings[0].equals(BUY)) {
                    buyResult += Integer.valueOf(strings[1]);
                } else {
                    supplyResult += Integer.valueOf(strings[1]);
                }
                value = reader.readLine();
            }
            int result = supplyResult - buyResult;
            writer.write(SUPPLY + "," + supplyResult + System.lineSeparator());
            writer.write(BUY + "," + buyResult + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException cat) {
            throw new RuntimeException("Can't open file" + cat);
        }
    }
}
