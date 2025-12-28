package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        Path pathOfTest = Path.of(fromFileName);
        try {
            List<String> readEachLine = Files.readAllLines(pathOfTest);
            for (String eachLine : readEachLine) {
                if (eachLine.isBlank()) {
                    continue;
                }
                String[] tokens = eachLine.split(",");
                if (tokens.length >= 2) {
                    String trimmedToken0 = tokens[0].trim();
                    String trimmedToken1 = tokens[1].trim();
                    if (trimmedToken0.equals("supply")) {
                        supply += Integer.parseInt(trimmedToken1);
                    } else if (trimmedToken0.equals("buy")) {
                        buy += Integer.parseInt(trimmedToken1);
                    }
                }
            }
            String report = "supply," + supply + System.lineSeparator() + "buy," + buy + System.lineSeparator() + "result," + (supply - buy);
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName));
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
