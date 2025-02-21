package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(new File(fromFileName)));
            String line = reader.readLine();
            while (line != null) {
                String[] value = line.split(",");
                if (value[0].equals("buy")) {
                    buy += Integer.valueOf(value[1]);
                }
                if (value[0].equals("supply")) {
                    supply += Integer.valueOf(value[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("can't read file", e);
        }
        int result = supply - buy;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
