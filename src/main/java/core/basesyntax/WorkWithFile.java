package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFrom(fromFileName);
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            if (line.contains(SUPPLY)) {
                supply += Integer.parseInt(line.split(",")[1]);
            } else {
                buy += Integer.parseInt(line.split(",")[1]);
            }
        }
        StringBuilder result = new StringBuilder().append("supply,").append(supply)
                .append(System.lineSeparator()).append("buy,").append(buy)
                .append(System.lineSeparator()).append("result,").append(supply - buy);
        writeTo(toFileName, result);
    }

    private List<String> readFrom(String fromFileName) {
        try {
            return Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to find: " + fromFileName, e);
        }
    }

    private void writeTo(String toFileName, StringBuilder message) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), message.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("ERROR: unable to write to: " + toFileName, e);
        }
    }
}
