package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String line;
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        int result = supply - buy;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
