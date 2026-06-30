package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        Path file = Path.of(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] line = value.split(",");
                if (line[0].equals("supply")) {
                    supply += Integer.parseInt(line[1]);
                } else {
                    buy += Integer.parseInt(line[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }

        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Could not write to file", e);
        }
    }
}
