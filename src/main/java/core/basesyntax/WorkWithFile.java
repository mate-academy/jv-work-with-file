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
        String [] data = readData(fromFileName);
        String report = createReport(data);
        writeData(toFileName, report);

    }

    private String[] readData(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant read file" + fileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeData(String fileName, String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + fileName, e);
        }
    }

    private String createReport(String[] data) {
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            String[] info = line.split(DELIMITER);
            String operation = info[TYPE_INDEX];
            int amount = Integer.parseInt(info[VALUE_INDEX]);

            if (operation.equals("supply")) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result + System.lineSeparator();

    }
}
