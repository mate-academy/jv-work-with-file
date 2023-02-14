package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int buy = 0;
            int supply = 0;
            String line = reader.readLine();
            while (line != null) {
                if (line.split(",")[0].equals("buy")) {
                    buy += Integer.parseInt(line.split(",")[1]);
                }
                if (line.split(",")[0].equals("supply")) {
                    supply += Integer.parseInt(line.split(",")[1]);
                }
                line = reader.readLine();
            }
            if (!Files.exists(new File(toFileName).toPath())) {
                Files.createFile(new File(toFileName).toPath());
            }
            Files.write(new File(toFileName).toPath(),new StringBuilder()
                    .append("supply,").append(supply)
                    .append(System.lineSeparator())
                    .append("buy,").append(buy)
                    .append(System.lineSeparator())
                    .append("result,")
                    .append(supply - buy).toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
