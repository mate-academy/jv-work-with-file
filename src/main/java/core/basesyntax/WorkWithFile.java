package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true));
            String value = reader.readLine();
            int buy = 0;
            int supply = 0;
            while (value != null) {
                String[] array = value.split(",");
                if (array[0].equals("supply")) {
                    supply += Integer.parseInt(array[1]);
                } else if (array[0].equals("buy")) {
                    buy += Integer.parseInt(array[1]);
                }
                value = reader.readLine();
            }
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + (supply - buy));
            writer.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("I can't read file", e);
        }
    }
}
