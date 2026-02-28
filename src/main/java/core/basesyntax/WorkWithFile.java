package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int DATA_OPERATION = 0;
    private static final int DATA_AMOUNT = 1;

    private List<String> readFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cant read file", e);
        }
    }

    private void writeFile(String toFileName, int supplyAmount, int buyAmount) {
        try {
            Files.writeString(Path.of(toFileName), "supply,"
                    + supplyAmount + System.lineSeparator()
                    + "buy," + buyAmount + System.lineSeparator() + "result,"
                    + (supplyAmount - buyAmount) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file: ", e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        int buyAmount = 0;
        int supplyAmount = 0;
        List<String> strings = readFile(fromFileName);
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
        writeFile(toFileName, supplyAmount, buyAmount);
    }
}
