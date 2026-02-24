package core.basesyntax;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int supply = 0;
        int buy = 0;
        for (String el : lines) {
            String[] words = el.split(",");
            if (words[0].equals("supply")) {
                supply += Integer.parseInt(words[1]);
            }
            else if (words[0].equals("buy")) {
                buy += Integer.parseInt(words[1]);
            }
        }
        int result = supply - buy;
        String report =
                "supply," + supply + System.lineSeparator()
                        + "buy," + buy + System.lineSeparator()
                        + "result," + result;
        try {
            Files.writeString(
                    Path.of(toFileName),
                    report,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
