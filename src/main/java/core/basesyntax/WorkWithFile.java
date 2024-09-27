package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Path.of(toFileName),
                StandardCharsets.UTF_8)
        ) {
            List<String> linesFromFile = Files.readAllLines(Path.of(fromFileName));
            DataRecord dataRecord = new DataRecord();
            for (String line : linesFromFile) {
                String[] splitStrings = line.split(",");
                if (splitStrings[0].equals("supply")) {
                    dataRecord.addSupply(Integer.parseInt(splitStrings[1]));
                } else if (splitStrings[0].equals("buy")) {
                    dataRecord.addBuy(Integer.parseInt(splitStrings[1]));
                }
            }
            bufferedWriter.write("supply"
                    + ","
                    + dataRecord.getStringSupply()
                    + System.lineSeparator());
            bufferedWriter.write("buy"
                    + ","
                    + dataRecord.getStringBuy()
                    + System.lineSeparator());
            bufferedWriter.write("result"
                    + ","
                    + dataRecord.getResult()
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read/write from/to file");
        }
    }
}
