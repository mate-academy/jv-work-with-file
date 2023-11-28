package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final String TO_BUY = "buy";
    private static final String TO_SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int DATA_INDEX_TO_DO = 0;
    private static final int DATA_INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(toFileName, report);
    }

    public String createReport(List<String> dataFromFile) {
        int supplyCounter = 0;
        int buyCounter = 0;

        for (String line : dataFromFile) {
            String[] data = line.split("\\W+");
            String action = data[DATA_INDEX_TO_DO];
            int amount = Integer.parseInt(data[DATA_INDEX_AMOUNT]);

            if (action.equals(TO_BUY)) {
                buyCounter += amount;
            } else {
                supplyCounter += amount;
            }
        }
        int result = supplyCounter - buyCounter;
        String report = TO_SUPPLY + "," + supplyCounter + System.lineSeparator()
                + TO_BUY + "," + buyCounter + System.lineSeparator()
                + RESULT + "," + result;

        return report;
    }

    public void writeReportToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file", e);
        }
    }

    public List<String> readDataFromFile(String fromFileName) {
        try {
            List<String> dataFromFile = Files.readAllLines(Path.of(fromFileName));
            return dataFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
    }
}

