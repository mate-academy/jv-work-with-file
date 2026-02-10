package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(SEPARATOR);
                if (SUPPLY.equals(parts[0].trim())) {
                    supply += Integer.parseInt(parts[1].trim());
                } else if (BUY.equals(parts[0].trim())) {
                    buy += Integer.parseInt(parts[1].trim());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + SEPARATOR + supply + System.lineSeparator());
            writer.write(BUY + SEPARATOR + buy + System.lineSeparator());
            writer.write("result" + SEPARATOR + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
