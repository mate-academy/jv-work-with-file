package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File csv = new File(toFileName);
        String lineSeparator = System.lineSeparator();
        int supplier= 0;
        int buy = 0;

        try {
            List<String> text = Files.readAllLines(file.toPath());
            for (String s : text) {
                String[] tmp = s.split(",");
                if (tmp[0].equals("supply")) {
                    supplier += Integer.parseInt(tmp[1]);
                } else {
                    buy += Integer.parseInt(tmp[1]);
                }
            }
            Files.write(csv.toPath(), new byte[0],
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            Files.writeString(csv.toPath(),
                    "supply," + supplier + lineSeparator, StandardOpenOption.APPEND);
            Files.writeString(csv.toPath(),
                    "buy," + buy + lineSeparator, StandardOpenOption.APPEND);
            Files.writeString(csv.toPath(),
                    "result," + (supplier - buy), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
