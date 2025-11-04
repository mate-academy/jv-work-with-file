package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readInfoFromFile(fromFileName);
        String statistic = getResultStatistic(lines);
        writeInfoToFile(toFileName, statistic);
    }

    private List<String> readInfoFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return lines;
    }

    private void writeInfoToFile(String toFileName, String data) {
        File file = new File(toFileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    private String getResultStatistic(List<String> lines) {
        StringBuilder statistic = new StringBuilder();
        int buyCount = 0;
        int supplyCount = 0;
        for (String line : lines) {
            String[] value = line.split(",");
            if (value[OPERATION_POSITION].equals(SUPPLY_OPERATION)) {
                supplyCount += Integer.parseInt(value[AMOUNT_POSITION]);
            } else {
                buyCount += Integer.parseInt(value[AMOUNT_POSITION]);
            }
        }
        statistic.append(SUPPLY_OPERATION).append(",").append(supplyCount)
                .append(System.lineSeparator()).append(BUY_OPERATION).append(",").append(buyCount)
                .append(System.lineSeparator()).append(RESULT_OPERATION)
                .append(",").append(supplyCount - buyCount);
        return statistic.toString();
    }
}
