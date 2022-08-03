package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int sumBuy = 0;
        int sumSupply = 0;
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        for (String line : strings) {
            int value = Integer.parseInt(line.substring(line.indexOf(",") + 1));
            if (line.substring(0, line.indexOf(",")).equals("buy")) {
                sumBuy += value;
            } else {
                sumSupply += value;
            }
        }
        File newFile = new File(toFileName);
        String result = "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + (sumSupply - sumBuy);
        try {
            Files.write(newFile.toPath(), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
