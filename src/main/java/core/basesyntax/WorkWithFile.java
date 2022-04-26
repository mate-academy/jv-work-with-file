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
        List<String> dataFile = getDataFile(fromFileName);
        StringBuilder statisticBuilder = getStatisticBuilder(dataFile);
        setStatisticFile(toFileName, statisticBuilder);
    }

    private List<String> getDataFile(String fromFileName) {
        List<String> data;
        try {
            File file = new File(fromFileName);
            data = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File can't read", e);
        }
        return data;
    }

    private StringBuilder getStatisticBuilder(List<String> data) {
        StringBuilder statisticBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int result = 0;
        for (String str : data) {
            String[] substring = str.split(",");
            if (substring[OPERATION_INDEX].equals("supply")) {
                supplySum += Integer.parseInt(substring[QUANTITY_INDEX]);
            }
            if (substring[OPERATION_INDEX].equals("buy")) {
                buySum += Integer.parseInt(substring[QUANTITY_INDEX]);
            }
        }
        result = supplySum - buySum;
        statisticBuilder.append("supply").append(",").append(supplySum)
                .append(System.lineSeparator()).append("buy").append(",")
                .append(buySum).append(System.lineSeparator())
                .append("result").append(",").append(result);
        return statisticBuilder;
    }

    private void setStatisticFile(String toFileName, StringBuilder stringBuilder) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("File can't write", e);
        }
    }
}
