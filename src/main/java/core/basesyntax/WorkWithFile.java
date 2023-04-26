package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                FileWriter writer = new FileWriter(toFileName)) {
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
                value = reader.readLine();
            }
            int result = supply - buy;
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file "
                    + fromFileName + toFileName, e);
        }
    }
}
