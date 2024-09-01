package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        List<String> fileData = null;

        try {
            fileData = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String data : fileData) {
            String[] values = data.split(",");

            String operation = values[0];
            String amount = values[1];

            if (operation.toUpperCase().equals(Operation.BUY.toString())) {
                buyAmount += Integer.parseInt(amount);
            }

            if (operation.toUpperCase().equals(Operation.SUPPLY.toString())) {
                supplyAmount += Integer.parseInt(amount);
            }
        }

        int result = supplyAmount - buyAmount;

        StringBuilder stringBuilder = new StringBuilder()
                .append("supply,")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result,")
                .append(result)
                .append(System.lineSeparator());

        try {
            Files.write(toFile.toPath(), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
