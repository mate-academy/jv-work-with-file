package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    static final int ACTION_INDEX = 0;
    static final int VALUE_INDEX = 1;
    static final String ACTION_SUPPLY = "supply";
    static final String ACTION_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writetoFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            return strings;
        } catch (IOException e) {
            throw new RuntimeException("Can not read from file" + fromFileName, e);
        }
    }

    private String createReport(List<String> dataFromFile) {
        String[] arraySplit;
        int supply = 0;
        int buy = 0;
        StringBuilder builder = new StringBuilder();
        for (String action : dataFromFile) {
            arraySplit = action.split(",");
            if (ACTION_SUPPLY.equals(arraySplit[ACTION_INDEX])) {
                supply += Integer.parseInt(arraySplit[VALUE_INDEX]);
            }
            if (ACTION_BUY.equals(arraySplit[ACTION_INDEX])) {
                buy += Integer.parseInt(arraySplit[VALUE_INDEX]);
            }
        }
        builder.append(ACTION_SUPPLY).append(",").append(supply).append(System.lineSeparator())
                .append(ACTION_BUY).append(",").append(buy).append(System.lineSeparator())
                .append("result").append(',').append(supply - buy);
        return builder.toString();
    }

    private void writetoFile(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(),report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file" + toFileName,e);
        }
    }
}
