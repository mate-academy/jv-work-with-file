package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            List<String> linesFromFile = Files.readAllLines(Path.of(fromFileName));
            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
            for (String line : linesFromFile) {
                String[] splitStrings = line.split(",");
                linkedHashMap.put(
                        splitStrings[0],
                        linkedHashMap.getOrDefault(splitStrings[0],
                                0
                        ) + Integer.parseInt(splitStrings[1]));
            }
            bufferedWriter.write("supply"
                    + ","
                    + linkedHashMap.get("supply")
                    + System.lineSeparator());
            bufferedWriter.write("buy"
                    + ","
                    + linkedHashMap.get("buy")
                    + System.lineSeparator());
            int result = linkedHashMap.get("supply") - linkedHashMap.get("buy");
            bufferedWriter.write("result"
                    + ","
                    + result
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
