package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        List<String> strings = null;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        for (String line : strings) {
            int value = Integer.valueOf(line.substring(line.indexOf(",") + 1, line.length()));
            if (line.substring(0, line.indexOf(",")).equals("supply")) {
                supply += value;
            } else {
                buy += value;
            }
        }

        File file1 = new File(toFileName);
        String result = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
        try {
            Files.write(file1.toPath(),result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }

    }
}
