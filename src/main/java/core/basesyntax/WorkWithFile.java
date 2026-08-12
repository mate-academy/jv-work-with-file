package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Path path = Path.of(fromFileName);
        List<String> lines;
        int supply = 0;
        int buy = 0;
        int resultOfBuy = 0;
        try {
            lines = Files.readAllLines(path);
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : lines) {
                stringBuilder.append(line).append(" ");
            }
            String[] result = stringBuilder.toString().split("\\s+");
            for (String line : result) {
                if (line.contains("supply")) {
                    supply += Integer.parseInt(line.split(",")[1]);
                }
                if (line.contains("buy")) {
                    buy += Integer.parseInt(line.split(",")[1]);
                }
            }
            resultOfBuy = supply - buy;
            String resultOut = "supply," + supply + "\nbuy," + buy + "\nresult," + resultOfBuy;
            FileWriter fileWriter = new FileWriter(toFileName);
            fileWriter.write(resultOut);
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
