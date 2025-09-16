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
            String line = reader.readLine();
            while (line != null) {
                if (line.contains("supply")) {
                    supply += Integer.parseInt(line.substring(line.indexOf(",") + 1));
                    line = reader.readLine();
                } else {
                    buy += Integer.parseInt(line.substring(line.indexOf(",") + 1));
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Not able to read a file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Not able to write a file", e);
        }
    }
}
