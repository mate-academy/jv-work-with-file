package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.nio.file.Path;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of(fromFileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2 && values[0].equals("supply")) {
                    totalSupply += Integer.parseInt(values[1]);
                } else if (values.length == 2 && values[0].equals("buy")) {
                    totalBuy += Integer.parseInt(values[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read this file", e);
        }

        int result = totalSupply - totalBuy;
        String resultStr = "supply," + totalSupply + "\n"
                + "buy," + totalBuy + "\n"
                + "result," + result;

        try {
            Files.writeString(Path.of(toFileName), resultStr);
        } catch (IOException  e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
