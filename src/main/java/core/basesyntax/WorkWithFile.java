package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final int NAME_INDEX = 0;
    private static final int SUM_INDEX = 1;
    private static final String SUPPLY_ITEM = "supply";
    private static final String BUY_ITEM = "buy";
    private static final String RESULT_ITEM = "result";
    private static final String RESULT_SPLIT = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
            Files.write(file.toPath(), createReport(readFromFile(fromFileName)).getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> statistic;
        try {
            file.createNewFile();
            statistic = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cant't read file " + fromFileName, e);
        }
        return statistic;
    }

    private String createReport(List<String> statistic) {
        int supply = 0;
        int buy = 0;
        int result;
        for (String items : statistic) {
            String[] item = items.split(RESULT_SPLIT);
            if (item[NAME_INDEX].equals(SUPPLY_ITEM)) {
                supply += Integer.parseInt(item[SUM_INDEX]);
            } else if (item[NAME_INDEX].equals(BUY_ITEM)) {
                buy += Integer.parseInt(item[SUM_INDEX]);
            }
        }
        result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_ITEM).append(RESULT_SPLIT).append(supply)
                .append(System.lineSeparator()).append(BUY_ITEM).append(RESULT_SPLIT)
                .append(buy).append(System.lineSeparator()).append(RESULT_ITEM)
                .append(RESULT_SPLIT).append(result);
        return report.toString();
    }
}
