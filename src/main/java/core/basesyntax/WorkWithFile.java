package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private String line;
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] waw = line.split(",");
                String opretion = waw[0];
                int amount = Integer.parseInt(waw[1]);
                if (opretion.equals("buy")) {
                    buy += amount;
                } else if (opretion.equals("supply")) {
                    supply += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fromFileName, e);
        }
    }
}
