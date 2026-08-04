package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAll = 0;
        int buyAll = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null && !value.isEmpty()) {
                String[] line = value.split(",");
                switch (line[0]) {
                    case ("supply"):
                        supplyAll += Integer.parseInt(line[1]);
                        break;
                    case ("buy"):
                        buyAll += Integer.parseInt(line[1]);
                        break;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAll + System.lineSeparator());
            writer.write("buy," + buyAll + System.lineSeparator());
            writer.write("result," + (supplyAll - buyAll) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
