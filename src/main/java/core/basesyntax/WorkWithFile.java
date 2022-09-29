package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public static final int NAME_INDEX = 0;
    public static final int SUM_INDEX = 1;
    public static final String SUPPLY_ITEM = "supply";
    public static final String BUY_ITEM = "buy";
    public static final String RESULT_ITEM = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        try {
            toFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + toFileName, e);
        }
        try {
            Files.write(toFile.toPath(), readFromFile(fromFileName).getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        int result;
        try {
            List<String> statistic = Files.readAllLines(fromFile.toPath());
            for (String items : statistic) {
                String[] item = items.split(",");
                if (item[NAME_INDEX].equals(SUPPLY_ITEM)) {
                    supply += Integer.parseInt(item[SUM_INDEX]);
                } else if (item[NAME_INDEX].equals(BUY_ITEM)) {
                    buy += Integer.parseInt(item[SUM_INDEX]);
                }
            }
            result = supply - buy;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        StringBuilder writeInfo = new StringBuilder();
        writeInfo.append(SUPPLY_ITEM).append(",").append(supply).append(System.lineSeparator())
                .append(BUY_ITEM).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT_ITEM).append(",").append(result);
        return writeInfo.toString();
    }
}
