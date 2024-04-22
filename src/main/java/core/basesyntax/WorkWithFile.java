package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public int[] getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fromFileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equals(operation)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operation)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка обробки файлів", e);
        }

        int result = supplyTotal - buyTotal;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Помилка запису в файл", e);
        }

        return new int[]{supplyTotal, buyTotal, result};
    }
}
