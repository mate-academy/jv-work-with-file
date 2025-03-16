package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        int supply = 0;
        int buy = 0;
        String[] data;

        try {

            List<String> lines = Files.readAllLines(Path.of(fromFileName));

            for (String line : lines) {

                data = line.trim().split(",");
                int value = Integer.parseInt(data[1]);

                if (data[0].equals("supply")) {
                    supply += value;
                } else {
                    buy += value;
                }
            }

            writeToFile(supply, buy, toFileName);

        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file.", e);
        }
    }

    private void writeToFile(int supply, int buy, String toFileName) throws RuntimeException{
        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file.", e);
        }
    }
}
