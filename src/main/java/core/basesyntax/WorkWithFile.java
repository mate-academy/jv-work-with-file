package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else if (data[0].equals("buy")) {
                    buy += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fromFileName, e);
        }

        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }
    }
}
