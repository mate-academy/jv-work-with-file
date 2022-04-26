package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int QUANTITY_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        String statisticBuilder = getStatisticParsed(lines);
        writeToFile(toFileName, statisticBuilder);
    }

    private List<String> readFromFile(String fromFileName) {
        List<String> lines;
        try {
            File file = new File(fromFileName);
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File can't read", e);
        }
        return lines;
    }

    private String getStatisticParsed(List<String> lines) {
        StringBuilder statisticBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int result = 0;
        for (String line : lines) {
            String[] splittedLine = line.split(",");
            if (splittedLine[OPERATION_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(splittedLine[QUANTITY_INDEX]);
            }
            if (splittedLine[OPERATION_INDEX].equals("buy")) {
                buySum += Integer.parseInt(splittedLine[QUANTITY_INDEX]);
            }
        }
        result = supplySum - buySum;
        statisticBuilder.append("supply").append(",").append(supplySum)
                .append(System.lineSeparator()).append("buy").append(",")
                .append(buySum).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return statisticBuilder.toString();
    }

    private void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("File can't write", e);
        }
    }
}
