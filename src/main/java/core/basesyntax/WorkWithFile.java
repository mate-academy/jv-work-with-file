package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int buyValue = 0;
        int supplyValue = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            String[] valueArray;

            while (value != null) {
                valueArray = value.split(",");
                if (valueArray[0].equals("buy")) {
                    buyValue += Integer.parseInt(valueArray[1]);
                } else if (valueArray[0].equals("supply")) {
                    supplyValue += Integer.parseInt(valueArray[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file.", e);
        }
        file = new File(toFileName);
        int result = supplyValue - buyValue;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("supply," + supplyValue);
            writer.newLine();
            writer.write("buy," + buyValue);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file.", e);
        }
    }
}
