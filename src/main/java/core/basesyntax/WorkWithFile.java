package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {

        List<String> lines = new ArrayList<>();
        File file = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                lines.add(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file", e);
        }

        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            if (line.startsWith(SUPPLY)) {
                String[] parts = line.split(",");
                String numberValue = parts[1];
                int value = Integer.parseInt(numberValue);
                supplyTotal += value;
            } else if (line.startsWith(BUY)) {
                String[] parts = line.split(",");
                String numberValue = parts[1];
                int value = Integer.parseInt(numberValue);
                buyTotal += value;
            }
        }
        int resultTotal = supplyTotal - buyTotal;
        File finalFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(finalFile))) {
            writer.write(SUPPLY + "," + supplyTotal);
            writer.newLine();
            writer.write(BUY + "," + buyTotal);
            writer.newLine();
            writer.write(RESULT + "," + resultTotal);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }

    }
}
