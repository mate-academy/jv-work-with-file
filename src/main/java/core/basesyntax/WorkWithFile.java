package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final int type = 0;
        final int amount = 1;
        int supply = 0;
        int buy = 0;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts[type].equals("buy")) {
                    buy += Integer.parseInt(parts[amount]);
                } else if (parts[type].equals("supply")) {
                    supply += Integer.parseInt(parts[amount]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file");
        }
        int result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file");
        }
    }
}
