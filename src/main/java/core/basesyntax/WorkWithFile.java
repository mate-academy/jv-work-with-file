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
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equalsIgnoreCase(operation)) {
                    supply += amount;
                } else if ("buy".equalsIgnoreCase(operation)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writeToFile(supply, buy, toFileName);
    }

    public void writeToFile(int supply, int buy, String toFileName) {
        int result = supply - buy;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + result + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
