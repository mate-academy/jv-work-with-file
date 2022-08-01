package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_PRODUCT = "supply";
    private static final String BUY_PRODUCT = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> allStatistics = new HashMap<>();
        allStatistics.put(SUPPLY_PRODUCT, 0);
        allStatistics.put(BUY_PRODUCT, 0);
        allStatistics.put(RESULT, 0);
        String[] fileData = readFile(fromFileName).split("\\W+");
        for (int i = INDEX_VALUE; i < fileData.length; i += 2) {
            allStatistics.put(fileData[i],
                    allStatistics.get(fileData[i])
                            + Integer.parseInt(fileData[i + INDEX_VALUE]));
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
        File file = new File(fromFileName);
        try {
            List<String> list = Files.readAllLines(file.toPath());
            return list.toString();
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
