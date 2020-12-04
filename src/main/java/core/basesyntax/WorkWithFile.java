package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }
        String[] rows = builder.toString().split(System.lineSeparator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            int supplyValue = 0;
            int buyValue = 0;
            for (String row : rows) {
                String[] data = row.split(",");
                if (data[0].equals("supply")) {
                    supplyValue += Integer.parseInt(data[1]);
                } else {
                    buyValue += Integer.parseInt(data[1]);
                }
            }
            writer.write("supply," + supplyValue);
            writer.newLine();
            writer.write("buy," + buyValue);
            writer.newLine();
            writer.write("result," + (supplyValue - buyValue));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file");
        }
    }
}
