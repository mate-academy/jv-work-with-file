package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        Path path = Path.of(file.toURI());
        List<String> lines;

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {

            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        String[] array = lines.toArray(new String[]{});
        int supplySum = 0;
        int buySum = 0;
        for (String data : array) {
            String[] split = data.split(",");
            if (split[0].equals("buy")) {
                buySum += Integer.parseInt(split[1]);
            } else {
                supplySum += Integer.parseInt(split[1]);
            }
        }
        String report = "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write(report);

        } catch (IOException e) {
            throw new RuntimeException("Can't write  " + toFileName, e);
        }
    }
}
