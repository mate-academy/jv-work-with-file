package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int TYPE_INDEX = 0;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) throws RuntimeException {
        int supply = readData(fromFileName, "supply");
        int buy = readData(fromFileName, "buy");
        String report = createReport(supply, buy);
        writeData(toFileName, report);

    }

    private int readData(String fileName, String type) {
        int sum = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(DELIMITER);
                if (parts[TYPE_INDEX].equals(type)) {
                    sum += Integer.parseInt(parts[VALUE_INDEX]);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file" + fileName, e);
        }
        return sum;
    }

    private void writeData(String fileName, String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + fileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();

    }
}
