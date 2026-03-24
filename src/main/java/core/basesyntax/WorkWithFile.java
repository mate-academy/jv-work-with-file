package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readData(fromFileName);
        String report = createReport(data[0], data[1]);
        writeToFile(toFileName, report);
    }

    private int[] readData(String fileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (operation.equals(SUPPLY)) {
                    supply += amount;
                } else if (operation.equals(BUY)) {
                    buy += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fileName, e);
        }

        return new int[]{supply, buy};
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;

        String report = SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + "result," + result;

        return report;
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
        System.out.println(report);
    }
}
