package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));

            int supplyTotal = 0;
            int buyTotal = 0;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 2) {
                    System.out.println("Invalid data format: " + line);
                    continue;
                }
                String operationType = data[0];
                int amount = Integer.parseInt(data[1]);

                if (operationType.equalsIgnoreCase(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operationType.equalsIgnoreCase(BUY)) {
                    buyTotal += amount;
                }
            }
            int result = supplyTotal - buyTotal;
            bufferedWriter.write("supply," + supplyTotal);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buyTotal);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (
        IOException e) {
            throw new RuntimeException("Can't read file:" + fromFileName, e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can`t close resource", e);
                }
            }
        }
    }
}
