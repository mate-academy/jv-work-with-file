package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fileName) {
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;

        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(SUPPLY)) {
                supply += Integer.parseInt(parts[1]);
            } else if (parts[0].equals(BUY)) {
                buy += Integer.parseInt(parts[1]);
            }
        }

        int result = supply - buy;
        return prepareData(supply, buy, result);
    }

    private void writeReportToFile(String report, String fileName) {
        try {
            Files.write(Paths.get(fileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String prepareData(int supply, int buy, int result) {
        return SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + result + System.lineSeparator();
    }
}

