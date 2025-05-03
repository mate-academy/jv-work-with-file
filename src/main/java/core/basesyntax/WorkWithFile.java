package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    if (line.toLowerCase().contains("operation type")) {
                        isFirstLine = false;
                        continue;
                    }
                }

                String[] parts = line.split(",");

                String operation = parts[0].trim().toLowerCase();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося прочитати файл: " + fromFileName, e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supplySum);
            bw.newLine();
            bw.write("buy," + buySum);
            bw.newLine();
            bw.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося записати дані у файл: " + toFileName, e);
        }
    }
}
