package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String CSV_SPLIT_BY = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(CSV_SPLIT_BY);
                String operation = data[0].trim();
                int amount = Integer.parseInt(data[1].trim());
                if (operation.equals("supply")) {
                    supplyAmount += amount;
                } else if (operation.equals("buy")) {
                    buyAmount += amount;
                }
            }
            int result = supplyAmount - buyAmount;
            bw.write("supply," + supplyAmount);
            bw.newLine();
            bw.write("buy," + buyAmount);
            bw.newLine();
            bw.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing file", e);
        }
    }
}


