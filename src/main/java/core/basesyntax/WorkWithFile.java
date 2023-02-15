package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        try {
            String[] lines = String.join(",", Files
                    .readAllLines(Paths.get(fromFileName), StandardCharsets.UTF_8)).split(",");
            for (int i = 0;i < lines.length; i++) {
                if (lines[i].equals("supply")) {
                    supply += Integer.parseInt(lines[i + 1]);
                }
                if (lines[i].equals("buy")) {
                    buy += Integer.parseInt(lines[i + 1]);
                }
            }
            result = supply - buy;
            String resultat = "supply," + supply
                    + "\r\n" + "buy," + buy
                    + "\r\n" + "result," + result;
            File resultFile = new File(toFileName);
            resultFile.createNewFile();
            Files.writeString(Paths.get(resultFile.toURI()), resultat, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException("can not do it!",e);
        }
    }
}
