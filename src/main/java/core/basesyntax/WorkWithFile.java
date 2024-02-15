package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = getReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        String data;
        try {
            data = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
        return data.split(System.lineSeparator());
    }

    private String getReport(String[] dataFromFile) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String lineFromFile : dataFromFile) {
            String[] lineData = lineFromFile.split(",");
            int amount = Integer.parseInt(lineData[SECOND_COLUMN]);
            switch (lineData[FIRST_COLUMN]) {
                case "buy" -> buyAmount += amount;
                case "supply" -> supplyAmount += amount;
                default -> System.err.println("Unknown operation type: "
                        + lineData[FIRST_COLUMN]);
            }
        }
        return "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + (supplyAmount - buyAmount);
    }

    private void writeToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
