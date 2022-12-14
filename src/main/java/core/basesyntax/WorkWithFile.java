package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int DATA_TYPE_INDEX = 0;
    private static final int DATA_VALUE_INDEX = 1;
    private static final int SUM_SUPPLY_INDEX = 0;
    private static final int SUM_BUY_INDEX = 1;
    private static final int SUM_RESULT_INDEX = 2;
    private static final int DATA_AMOUNT = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> readData = readFile(new File(fromFileName));
        String statistics = generateStat(readData);
        writeFile(statistics, new File(toFileName));
    }

    private List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
    }

    private String generateStat(List<String> readData) {
        int[] readStats = new int[DATA_AMOUNT];
        for (String foundInfo : readData) {
            String[] info = foundInfo.split("\\W+");
            if (info[DATA_TYPE_INDEX].equals("supply")) {
                readStats[SUM_SUPPLY_INDEX] += Integer.parseInt(info[DATA_VALUE_INDEX]);
            } else {
                readStats[SUM_BUY_INDEX] += Integer.parseInt(info[DATA_VALUE_INDEX]);
            }
        }
        StringBuilder builder = new StringBuilder();
        readStats[SUM_RESULT_INDEX] = readStats[SUM_SUPPLY_INDEX] - readStats[SUM_BUY_INDEX];
        builder.append("supply,").append(readStats[SUM_SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(readStats[SUM_BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(readStats[SUM_RESULT_INDEX]);
        String result = builder.toString();
        builder.setLength(0);
        return result;
    }

    private void writeFile(String statistics, File fileToWrite) {
        try {
            Files.write(fileToWrite.toPath(), statistics.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file", e);
        }
    }
}
