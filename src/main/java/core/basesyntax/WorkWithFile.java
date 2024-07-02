package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.startsWith("supply,")) {
                    String supplyResult = value.substring(value.indexOf(',') + 1);
                    supply += Integer.valueOf(supplyResult);
                } else {
                    String buyResult = value.substring(value.indexOf(',') + 1);
                    buy += Integer.valueOf(buyResult);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        int result = supply - buy;
        String[] resultArray = {
                "supply," + supply,
                "buy," + buy,
                "result," + result
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(" ");
            for (String object : resultArray) {
                writer.write(object);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
