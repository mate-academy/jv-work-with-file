package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                String operationType = cells[0];
                int amount = Integer.parseInt(cells[1]);

                if (operationType.equals("supply")) {
                    supplySum += amount;
                } else if (operationType.equals("buy")) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String supplyLine = "supply," + supplySum;
        String buyLine = "buy," + buySum;
        int resultSum = supplySum - buySum;
        String resultLine = "result," + resultSum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(supplyLine);
            writer.newLine();
            writer.write(buyLine);
            writer.newLine();
            writer.write(resultLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}