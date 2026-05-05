package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String result;
        try {
            List<String> lines = Files.readAllLines(new File(fromFileName).toPath());
            String[] linesArray = lines.toArray(new String[0]);
            String[] temp = new String[2];
            for (String line : linesArray) {
                temp = line.split(",");
                switch (temp[0]) {
                    case "supply":
                        supply += Integer.parseInt(temp[1]);
                        break;
                    case "buy":
                        buy += Integer.parseInt(temp[1]);
                        break;
                    default:
                        break;
                }
            }
            result = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can not read file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file", e);
        }

    }
}
