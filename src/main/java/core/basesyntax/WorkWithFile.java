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
                if (parts.length != 2) {
                    continue; // pomijamy niepoprawne linie
                }

                String operation = parts[0].trim();
                int amount;
                try {
                    amount = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    continue; // pomijamy linie, które nie mają liczby
                }

                if ("supply".equalsIgnoreCase(operation)) {
                    supply += amount;
                } else if ("buy".equalsIgnoreCase(operation)) {
                    buy += amount;
                }
            }

            int result = supply - buy;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write(String.format("supply,%d%n", supply));
                writer.write(String.format("buy,%d%n", buy));
                writer.write(String.format("result,%d%n", result));
            }

        } catch (IOException e) {
            throw new RuntimeException("Error processing files: " + e.getMessage(), e);
        }
    }
}
