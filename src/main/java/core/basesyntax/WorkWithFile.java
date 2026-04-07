package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int ACTION = 0;
    private static final int NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(toFileName);
        String result;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            int supplyAmount = 0;
            int buyAmount = 0;

            while ((line = reader.readLine()) != null) {
                String[] part = line.split(",");
                if (part[ACTION].equals("supply")) {
                    supplyAmount += Integer.parseInt(part[NUMBER]);
                } else {
                    buyAmount += Integer.parseInt(part[NUMBER]);
                }
            }
            result = "supply," + supplyAmount + System.lineSeparator()
                    + "buy," + buyAmount + System.lineSeparator()
                    + "result," + (supplyAmount - buyAmount);
            Files.write(file.toPath(), result.getBytes());
        } catch (IOException y) {
            throw new RuntimeException(y);
        }

    }
}
