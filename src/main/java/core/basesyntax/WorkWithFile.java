package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String supply = "supply";
    private static final String buy = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null && !value.isEmpty()) {
                list.add(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file", e);
        }

        int buyAmount = 0;
        int supplyAmount = 0;
        int result = 0;

        for (String value : list) {
            String[] tmp = value.split(",");
            if (tmp[0].equals(supply)) {
                if (tmp[1] != null && !tmp[1].isEmpty()) {
                    supplyAmount += Integer.parseInt(tmp[1]);
                }
            } else if (tmp[0].equals(buy)) {
                if (tmp[1] != null && !tmp[1].isEmpty()) {
                    buyAmount += Integer.parseInt(tmp[1]);
                }
            }
        }

        result = supplyAmount - buyAmount;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(supply + "," + supplyAmount + System.lineSeparator());
            writer.write(buy + "," + buyAmount + System.lineSeparator());
            writer.write(RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't save to a file", e);
        }
    }
}
