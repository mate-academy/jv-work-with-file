package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] readFileData = new String[2];
        int buyValue = 0;
        int supplyValue = 0;
        String value;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            while ((value = reader.readLine()) != null) {
                readFileData = value.split(",");
                if (readFileData[0].equals("buy")) {
                    buyValue += Integer.valueOf(readFileData[1]);
                } else {
                    supplyValue += Integer.valueOf(readFileData[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyValue);
            writer.newLine();
            writer.write("buy," + buyValue);
            writer.newLine();
            int result = supplyValue - buyValue;
            writer.write("result," + result);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
