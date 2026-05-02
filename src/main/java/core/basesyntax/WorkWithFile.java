package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try {
            String text = Files.readString(Path.of(fromFileName));

            String[] lines = text.split("\n");

            int moneyBuy = 0;
            int moneySupply = 0;
            int result;

            for (String string : lines) {
                string = string.trim();

                if (string.startsWith("buy,")) {

                    moneyBuy += Integer.parseInt(string.substring(4));

                } else if (string.startsWith("supply,")) {

                    moneySupply += Integer.parseInt(string.substring(7));

                }

            }

            result = moneySupply - moneyBuy;

            try (FileWriter writer = new FileWriter(toFileName)) {
                writer.write("supply," + moneySupply + System.lineSeparator());
                writer.write("buy," + moneyBuy + System.lineSeparator());
                writer.write("result," + result + System.lineSeparator());
            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }
}
