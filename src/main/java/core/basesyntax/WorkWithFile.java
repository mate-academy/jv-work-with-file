package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        String[] sortedInfo = getSortedInfo(fromFileName);
        String fileInfo = String.join("\r\n", sortedInfo);
        writeToFile(fileInfo, toFileName);
    }

    public String[] getSortedInfo(String fromFileName) throws IOException {
        var lines = Files.readAllLines(Paths.get(fromFileName));
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String type = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (type.equals("supply")) {
                    supply += amount;
                } else if (type.equals("buy")) {
                    buy += amount;
                }
            }
        }

        int result = supply - buy;
        return new String[]{
                "supply," + supply,
                "buy," + buy,
                "result," + result
        };
    }

    public void writeToFile(String fileInfo, String toFileName) throws IOException {
        Files.writeString(
                Paths.get(toFileName),
                fileInfo,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }
}

