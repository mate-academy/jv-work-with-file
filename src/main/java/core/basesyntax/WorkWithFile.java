package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value;

            while ((value = reader.readLine()) != null) {
                String[] valueParts = value.split(",");
                int amount = Integer.parseInt(valueParts[1]);

                if ("buy".equals(valueParts[0])) {
                    totalBuy += amount;
                }

                if ("supply".equals(valueParts[0])) {
                    totalSupply += amount;
                }
            }

            int result = totalSupply - totalBuy;

            List<String> resultAll = List.of(
                    "supply," + totalSupply,
                    "buy," + totalBuy,
                    "result," + result
            );

            Files.write(Path.of(toFileName), resultAll);
        } catch (IOException e) {
            throw new RuntimeException("Can't work with file", e);
        }
    }
}
