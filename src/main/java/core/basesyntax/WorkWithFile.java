package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "buy":
                        buy += Integer.parseInt(parts[1]);
                        break;
                    case "supply":
                        supply += Integer.parseInt(parts[1]);
                        break;
                    default:
                        break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }

        String resultToFile = "supply," + supply + System.lineSeparator()
                            + "buy," + buy + System.lineSeparator()
                            + "result," + (supply - buy);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultToFile);

        } catch (Exception e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
