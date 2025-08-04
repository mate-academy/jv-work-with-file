package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = null;
            int supply = 0;
            int buy = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    buy += Integer.parseInt(parts[1]);
                }
            }
            builder.append("supply,").append(supply).append(System.lineSeparator());
            builder.append("buy,").append(buy).append(System.lineSeparator());
            builder.append("result,").append(supply - buy);

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String report = builder.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write(report);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
