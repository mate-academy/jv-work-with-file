package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] lineArray = line.split(",");
                if (lineArray[0].equals("supply")) {
                    supply += Integer.parseInt(lineArray[1]);
                } else if (lineArray[0].equals("buy")) {
                    buy += Integer.parseInt(lineArray[1]);
                }
                line = reader.readLine();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write("supply," + supply + System.lineSeparator()
                        + "buy," + buy + System.lineSeparator()
                        + "result," + (supply - buy) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

}
