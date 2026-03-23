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
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int value = Integer.parseInt(parts[1]);

                if ("supply".equals(parts[0])) {
                    supply += value;
                } else if ("buy".equals(parts[0])) {
                    buy += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        int result = supply - buy;
        String ls = System.lineSeparator();

        String output = "supply," + supply + ls
                + "buy," + buy + ls
                + "result," + result;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(output);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
