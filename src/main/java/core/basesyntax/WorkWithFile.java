package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;

            while ((line = reader.readLine()) != null) { 
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid file format at line: " + line);
                }

                String definition = parts[0];
                int amount;
                try {
                    amount = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in line: " + line, e);
                }

                if (definition.equals(BUY)) {
                    buy += amount;
                } else if (definition.equals(SUPPLY)) {
                    supply += amount;
                } else {
                    throw new IllegalArgumentException("Unknown operation: " + definition);
                }
            }

            int result = supply - buy;
            createReport(toFileName, buy, supply, result);

        } catch (IOException e) {
            throw new RuntimeException("Error while reading the file: " + fromFileName, e);
        }
    }

    private void createReport(String toFileName, int buy, int supply, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(SUPPLY + "," + supply + System.lineSeparator());
            writer.write(BUY + "," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to the file: " + toFileName, e);
        }
    }
}
