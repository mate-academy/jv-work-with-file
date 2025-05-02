package core.basesyntax;

import java.io.BufferedReader;
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
                String[] data = line.split(",");
                if (data.length == 2) {
                    String operationType = data[0].trim();
                    int amount = Integer.parseInt(data[1].trim());

                    if ("supply".equalsIgnoreCase(operationType)) {
                        supply += amount;
                    } else if ("buy".equalsIgnoreCase(operationType)) {
                        buy += amount;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = supply - buy;

        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
