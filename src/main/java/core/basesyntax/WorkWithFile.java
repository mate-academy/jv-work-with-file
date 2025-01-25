package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        File file = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineWords = line.split(",");
                String operation = lineWords[0].trim();
                int value = Integer.parseInt(lineWords[1]);
                if (operation.equals("supply")) {
                    supply += value;
                }
                if (operation.equals("buy")) {
                    buy += value;
                }
                line = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Problem reading file", ex);
        }

        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
