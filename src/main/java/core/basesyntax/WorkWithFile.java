package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class WorkWithFile {
    private static final String SUPPLY_PRODUCT = "supply";
    private static final String BUY_PRODUCT = "buy";
    private static final String RESULT = "result";
    private static final int START_POINT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> allStatistics = new HashMap<>();
        allStatistics.put(SUPPLY_PRODUCT, 0);
        allStatistics.put(BUY_PRODUCT, 0);
        allStatistics.put(RESULT, 0);
        String[] fileData = readFile(fromFileName).split("\\W+");
        for (int i = 0; i < fileData.length; i += 2) {
            allStatistics.put(
                    fileData[i],
                    allStatistics.get(fileData[i])
                            + Integer.parseInt(fileData[i + START_POINT])
            );
        }
        allStatistics.put(RESULT, allStatistics.get(SUPPLY_PRODUCT)
                - allStatistics.get(BUY_PRODUCT));
        String finalDataToFile = buildReport(allStatistics);
        writeFile(toFileName, finalDataToFile);
    }

    private String buildReport(HashMap allStatistics) {
        StringBuilder buildReport = new StringBuilder();
        buildReport.append(SUPPLY_PRODUCT).append(",")
                .append(allStatistics.get(SUPPLY_PRODUCT)).append(System.lineSeparator());
        buildReport.append(BUY_PRODUCT).append(",")
                .append(allStatistics.get(BUY_PRODUCT)).append(System.lineSeparator());
        buildReport.append(RESULT).append(",")
                .append(allStatistics.get(RESULT)).append(System.lineSeparator());
        return buildReport.toString();
    }

    private String readFile(String fromFileName) {
        try {
            return Files.readString(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void writeFile(String toFileName, String dataReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(dataReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + toFileName, e);
        }
    }
}
