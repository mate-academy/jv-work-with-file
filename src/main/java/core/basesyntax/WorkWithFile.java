package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String report = processFile(fromFileName);
        writeFile(toFileName, report);
    }

    private String processFile(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] parts = value.split(",");
                if (parts.length != 2) {
                    System.err.println("Skipping invalid line " + value);
                    continue;
                }

                String operationType = parts[0];
                try {
                    int amount = Integer.parseInt(parts[1]);
                    if (operationType.equals("supply")) {
                        supply += amount;
                    } else if (operationType.equals("buy")) {
                        buy += amount;
                    } else {
                        System.err.println("Unknown operation type (skipping): " + value);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format (skipping): " + value);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file: " + fromFileName, e);
        }

        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    private void writeFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file:" + toFileName, e);
        }
    }
}
