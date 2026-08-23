package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readData(fromFileName);
        String report = createReport(data);
        writeToFile(toFileName, report);
    }

    private String readData(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        int supply = 0;
        int buy = 0;

        if (!data.isEmpty()) {
            String[] lines = data.split(System.lineSeparator());
            for (String line : lines) {
                String[] parts = line.split(CSV_SEPARATOR);
                if (parts[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals(BUY)) {
                    buy += Integer.parseInt(parts[1]);
                }
            }
        }
        int result = supply - buy;
        return SUPPLY + CSV_SEPARATOR + supply + System.lineSeparator()
                + BUY + CSV_SEPARATOR + buy + System.lineSeparator()
                + "result" + CSV_SEPARATOR + result;
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
