package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String newline;
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            while ((newline = bufferedReader.readLine()) != null) {
                String[] parts = newline.split(",");
                if (parts.length == 2) {
                    String category = parts[0];
                    int value = Integer.parseInt(parts[1]);
                    if (category.equals("supply")) {
                        supplyTotal += value;
                    } else if (category.equals("buy")) {
                        buyTotal += value;
                    }
                }
            }

            for (String s : Arrays.asList("supply," + supplyTotal, "buy," + buyTotal)) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("result," + (supplyTotal - buyTotal));
        } catch (IOException ex) {
            throw new RuntimeException("Can't read from or write to the file: " + ex.getMessage());
        }
    }
}
