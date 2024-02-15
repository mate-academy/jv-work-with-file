package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsAndValues = line.split(",");
                // Отримання типу операції (перший елемент масиву)
                String operationType = wordsAndValues[0];
                // Отримання кількості (другий елемент масиву) та перетворення його в ціле число
                int amount = Integer.parseInt(wordsAndValues[1]);

                if ("supply".equals(operationType)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operationType)) {
                    buyTotal += amount;
                }
            }

            // Записуємо результати у вихідний файл
            writer.write("supply," + supplyTotal);
            writer.newLine();
            writer.write("buy," + buyTotal);
            writer.newLine();
            writer.write("result," + (supplyTotal - buyTotal));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading/writing file", e);
        }
    }
}

