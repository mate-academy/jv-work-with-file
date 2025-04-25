package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                String operation = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());

                if (operation.equals("supply")) {
                    supply += value;
                } else if (operation.equals("buy")) {
                    buy += value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
