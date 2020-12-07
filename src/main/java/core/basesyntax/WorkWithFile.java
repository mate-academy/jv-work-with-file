package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String[] rows = builder.toString().split(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            int supplyValue = 0;
            int buyValue = 0;
            for (String row : rows) {
                String[] data = row.split(",");
                if (data[0].equals(SUPPLY)) {
                    supplyValue += Integer.parseInt(data[1]);
                } else if (data[0].equals(BUY)) {
                    buyValue += Integer.parseInt(data[1]);
                }
            }
            writer.write(SUPPLY + "," + supplyValue);
            writer.newLine();
            writer.write(BUY + "," + buyValue);
            writer.newLine();
            writer.write("result," + (supplyValue - buyValue));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }
}
