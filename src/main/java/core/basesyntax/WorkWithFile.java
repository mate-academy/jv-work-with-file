package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
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
            String supplyText = "supply"
                    + ","
                    + dataRecord.getSupply()
                    + System.lineSeparator();
            String buyText = "buy"
                    + ","
                    + dataRecord.getBuy()
                    + System.lineSeparator();
            String resultText = "result"
                    + ","
                    + dataRecord.getResult()
                    + System.lineSeparator();
            writeStringToFile(toFileName, supplyText, buyText, resultText);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file");
        }
    }

    public void writeStringToFile(String fileName, String... texts) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Path.of(fileName))
        ) {
            for (String text : texts) {
                bufferedWriter.write(text);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            System.out.println("не вийшло записати до файлу " + fileName);
        }
    }
}
