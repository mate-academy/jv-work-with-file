package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataList = readFile(fromFileName);
        String statisticContent = makeStatistic(dataList);
        writeFile(statisticContent, toFileName);
    }

    private String makeStatistic(List<String> data) {
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            if (line.contains(SUPPLY)) {
                String supplyValue = line.split(",")[1];
                supply += Integer.parseInt(supplyValue);
            } else if (line.contains(BUY)) {
                String buyValue = line.split(",")[1];
                buy += Integer.parseInt(buyValue);
            }
        }

        int result = supply - buy;
        return formatStatistic(supply, buy, result);
    }

    private String formatStatistic(int supply, int buy, int result) {
        return new StringBuilder()
                .append(SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(result)
                .toString();
    }

    private void writeFile(String statisticContent, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(statisticContent);
        } catch (IOException e) {
            throw new RuntimeException("Error during file writing", e);
        }
    }

    private List<String> readFile(String fileName) {
        File file = new File(fileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed reading the file", e);
        }
    }
}
