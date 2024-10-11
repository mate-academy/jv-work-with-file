package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File firstFile = new File(fromFileName);
        File secondFile = new File(toFileName);

        int buy = 0;
        int supply = 0;
        int result = 0;

        try {
            List<String> stringList = Files.readAllLines(firstFile.toPath());
            for (String s : stringList) {
                if (s.contains("buy")) {
                    buy += Integer.parseInt(s.substring(s.indexOf(',') + 1, s.length()));
                }
                if (s.contains("supply")) {
                    supply += Integer.parseInt(s.substring(s.indexOf(',') + 1, s.length()));
                }
            }

            result = supply - buy;

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(result).append(System.lineSeparator());

            secondFile.createNewFile();
            Files.write(secondFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
