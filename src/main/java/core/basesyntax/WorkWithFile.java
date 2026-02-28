package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public static final int DATA_OPERATION = 0;
    public static final int DATA_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
        int buyAmount = 0;
        int supplyAmount = 0;

        for (String str : strings) {
            String[] parts = str.split(",");
            String operation = parts[DATA_OPERATION];
            int amount = Integer.parseInt(parts[DATA_AMOUNT]);
            if (operation.equals("buy")) {
                buyAmount += amount;
            } else if (operation.equals("supply")) {
                supplyAmount += amount;
            }
        }
        String result = "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator() + "result,"
                + (supplyAmount - buyAmount) + System.lineSeparator();
        try {
            Files.writeString(Path.of(toFileName), result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file: ", e);
        }
    }
}
