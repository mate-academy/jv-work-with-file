package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            if (fromFileName == null) {
                throw new RuntimeException("No such file");
            }
            List<String> stringList = Files.readAllLines(Paths.get(fromFileName));
            int supplyCount = 0;
            int buyCount = 0;
            int resultCount;
            for (String line : stringList) {
                if (line.startsWith("supply")) {
                    supplyCount += Integer.parseInt(line.replaceAll("\\D+",""));
                } else if (line.startsWith("buy")) {
                    buyCount += Integer.parseInt(line.replaceAll("\\D+",""));
                }
            }
            resultCount = supplyCount - buyCount;
            fileWriter.write("supply," + supplyCount + "\n");
            fileWriter.write("buy," + buyCount + "\n");
            fileWriter.write("result," + resultCount + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
