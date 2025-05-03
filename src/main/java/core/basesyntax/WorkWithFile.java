package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_PART = 0;
    private static final int VALUE_PART = 1;
    private static final int FIRST_VALUE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        generateReport(fromFileName);
        writeToFile(fromFileName, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + e);
        }
    }

    private String generateReport(String fromFileName) {
        int supply = FIRST_VALUE;
        int buy = FIRST_VALUE;

        List<String> strings = readFromFile(fromFileName);
        for (String part : strings) {
            String[] parts = part.split(",");
            if (parts[OPERATION_PART].equals("supply")) {
                supply += Integer.parseInt(parts[VALUE_PART]);
            } else {
                buy += Integer.parseInt(parts[VALUE_PART]);
            }
        }
        int result = supply - buy;
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + result;
    }

    private void writeToFile(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String report = generateReport(fromFileName);
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + e);
        }
    }
}
