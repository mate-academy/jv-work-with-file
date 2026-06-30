package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Path filePath = Path.of(fromFileName);

        if (Files.exists(filePath)) {
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                String line;
                String[] keys = new String[100];
                int[] values = new int[100];
                int size = 0;

                while ((line = reader.readLine()) != null) {
                    String[] elements = line.split(",");

                    String key = elements[0];
                    int value = Integer.parseInt(elements[1]);

                    boolean found = false;

                    for (int i = 0; i < size; i++) {
                        if (keys[i].equals(key)) {
                            values[i] += value;
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        keys[size] = key;
                        values[size] = value;
                        size++;
                    }
                }

                int supply = 0;
                int buy = 0;

                for (int i = 0; i < size; i++) {
                    if ("supply".equals(keys[i])) {
                        supply = values[i];
                    } else if ("buy".equals(keys[i])) {
                        buy = values[i];
                    }
                }

                try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
                    writer.write("supply," + supply);
                    writer.newLine();

                    writer.write("buy," + buy);
                    writer.newLine();

                    writer.write("result," + (supply - buy));
                }
            } catch (IOException e) {
                System.out.println("Cant read file");
            }
        } else {
            System.out.println("File not exists");
        }
    }
}
