package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String report = generateReport(readFromFile(fromFileName));
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }

        if (content.toString().isBlank()) {
            throw new IllegalArgumentException("The file " + fromFileName + " is empty.");
        }

        return content.toString().split(System.lineSeparator());
    }

    private String generateReport(String[] lines) {
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] parts = line.split(COMMA);
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid file format at line: " + line);
            }
            try {
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals(SUPPLY)) {
                    supply += amount;
                } else if (operation.equals(BUY)) {
                    buy += amount;
                } else {
                    throw new IllegalArgumentException("Unknown operation: " + operation);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in line: " + line, e);
            }
        }

        int result = supply - buy;

        return String.format("%s,%d%n%s,%d%n%s,%d", SUPPLY, supply, BUY, buy, RESULT, result);
    }

    private void writeToFile(String toFileName, String report) {
        try {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                bufferedWriter.write(report);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
