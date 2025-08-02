package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File toFilePath = new File(toFileName);
            File fromFilePath = new File(fromFileName);
            FileWriter fileWriter = new FileWriter(toFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int supply = 0;
            int buy = 0;
            int result = 0;

            List<String> fileContent = Files.readAllLines(fromFilePath.toPath());

            for (String line : fileContent) {
                if (line.startsWith("supply")) {
                    supply += Integer.parseInt(line.substring(line.indexOf(",") + 1));
                } else if (line.startsWith("buy")) {
                    buy += Integer.parseInt(line.substring(line.indexOf(",") + 1));
                }
            }
            result += supply - buy;

            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
