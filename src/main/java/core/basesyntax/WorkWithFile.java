package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int countSupply = 0;
        int countBuy = 0;
        try {
            List<String> allLines = Files.readAllLines(new File(fromFileName).toPath());
            for (String line : allLines) {
                if (line.matches("^[^,]+,[^,]+$")) {
                    String[] splited = line.split(",");
                    if (splited[0].equals("supply")) {
                        countSupply += Integer.parseInt(splited[1]);
                    } else {
                        countBuy += Integer.parseInt(splited[1]);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("It is not able to read file!", e);
        }

        String result = "supply," + countSupply + System.lineSeparator()
                + "buy," + countBuy + System.lineSeparator()
                + "result," + (countSupply - countBuy);

        try {
            Files.write(new File(toFileName).toPath(), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("It is not able to write to file!", e);
        }
    }
}
