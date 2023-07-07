package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            supply = readData(fromFileName, "supply");
            buy = readData(fromFileName, "buy");
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }

        int result = supply - buy;

        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator());
        report.append("buy,").append(buy).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());

        try {
            writeData(toFileName, report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private int readData(String fileName, String operationType) throws IOException {
        int total = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts[OPERATION_INDEX].equals(operationType)) {
                    total += Integer.parseInt(parts[VALUE_INDEX]);
                }
            }
        }
        return total;
    }

    private void writeData(String fileName, String data) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            writer.write(data);
        }
    }
}
