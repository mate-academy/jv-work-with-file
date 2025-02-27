package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final String FIRST_KEYWORD = "supply";
    public static final String SECOND_KEYWORD = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int[] result = getStatisticValues(file);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(FIRST_KEYWORD).append(",").append(result[0])
                .append(System.lineSeparator()).append(SECOND_KEYWORD).append(",")
                .append(result[1]).append(System.lineSeparator()).append("result,")
                .append(result[2]);

        Path path = Path.of(toFileName);
        try {
            Files.writeString(path, stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException("Can't get statistic", e);
        }
    }

    public int[] getStatisticValues(File statisticFile) {
        int supplySum = 0;
        int buySum = 0;
        int resultSum;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(statisticFile));
            String value = bufferedReader.readLine();
            while (value != null) {
                int parseInt = Integer.parseInt(value.substring(value.indexOf(',') + 1));
                if (value.contains(FIRST_KEYWORD)) {
                    supplySum += parseInt;
                } else if (value.contains(SECOND_KEYWORD)) {
                    buySum += parseInt;
                }
                value = bufferedReader.readLine();
            }
            resultSum = (supplySum > buySum) ? (supplySum - buySum) : (buySum - supplySum);
            return new int[]{supplySum, buySum, resultSum};
        } catch (IOException e) {
            throw new RuntimeException("Can't get statistic values", e);
        }
    }
}
