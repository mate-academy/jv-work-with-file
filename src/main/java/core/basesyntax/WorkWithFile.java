package core.basesyntax;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            for (String line : lines) {
                String[] data = line.split(",");
                if (data[0].equals("supply")) {
                    supply += Integer.parseInt(data[1]);
                } else if (data[0].equals("buy")) {
                    buy += Integer.parseInt(data[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int result = supply - buy;
        String output = String.join(System.lineSeparator(),
                "supply," + supply,
                "buy," + buy,
                "result," + result);

        try {
            Files.writeString(Path.of(toFileName), output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
