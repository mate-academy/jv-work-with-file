package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            List<String> strings = Files.readAllLines(file.toPath());

            for (String part : strings) {
                String[] parts = part.split(",");
                if (parts[0].equals("supply")) {
                    supply += Integer.parseInt(parts[1]);
                } else {
                    buy += Integer.parseInt(parts[1]);
                }
            }
            int result = supply - buy;
            String report = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result;
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
    }
}
