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

        try (BufferedReader reader = new BufferedReader(
                new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                if (value.charAt(0) == 's') {
                    String[] parts = value.split(",");
                    supply += Integer.parseInt(parts[1]);
                }

                if (value.charAt(0) == 'b') {
                    String[] parts = value.split(",");
                    buy += Integer.parseInt(parts[1]);
                }

                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(toFileName, false))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
