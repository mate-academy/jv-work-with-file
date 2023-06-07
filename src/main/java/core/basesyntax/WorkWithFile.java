package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            int supplyTotal = 0;
            int buyTotal = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String operationType = fields[0];
                int amount = Integer.parseInt(fields[1]);
                if (operationType.equals("supply")) {
                    supplyTotal += amount;
                } else if (operationType.equals("buy")) {
                    buyTotal += amount;
                }
            }
            int result = supplyTotal - buyTotal;
            bufferedWriter.write("supply," + supplyTotal);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buyTotal);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
