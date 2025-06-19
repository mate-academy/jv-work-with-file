package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) throws IOException {
        List<String> fileInfo = List.of(getSortedInfo(fromFileName));
        writeToFile(fileInfo,toFileName);

    }

    public String[] getSortedInfo(String fromFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fromFileName));
        int supply = 0;
        int buy = 0;

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts.length == 2) {
                String type = parts[0].trim();
                String amountStr = parts[1].trim();
                int amount = Integer.parseInt(amountStr);

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

    public void writeToFile(List<String> fileInfo,String toFileName) throws IOException {
        if (Files.notExists(Path.of(toFileName))) {
            Files.createFile(Path.of(toFileName));
        }
        Files.write(Paths.get(toFileName), fileInfo);
    }
}


