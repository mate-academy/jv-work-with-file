package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

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
            int amount = Integer.parseInt(lineData[AMOUNT]);
            switch (lineData[OPERATION_TYPE]) {
                case "buy" -> buyAmount += amount;
                case "supply" -> supplyAmount += amount;
                default -> throw new RuntimeException("Unknown operation type: "
                        + lineData[OPERATION_TYPE]);
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
