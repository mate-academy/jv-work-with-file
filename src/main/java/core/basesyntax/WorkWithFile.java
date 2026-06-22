package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    // Змінили void на String, бо метод має повертати результат
    public String getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        // 1. Читаємо файл (твоя логіка)
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                if (row[0].equals("supply")) {
                    totalSupply += Integer.parseInt(row[1]);
                } else if (row[0].equals("buy")) {
                    totalBuy += Integer.parseInt(row[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        int result = totalSupply - totalBuy;

        // 2. Створюємо звіт за допомогою StringBuilder (як просить система)
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(totalSupply).append(System.lineSeparator());
        report.append("buy,").append(totalBuy).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());

        // Перетворюємо StringBuilder у звичайний рядок
        String finalReport = report.toString();

        // 3. Записуємо цей готовий рядок у новий файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(finalReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }

        // 4. Повертаємо звіт (вимоги завдання виконано!)
        return finalReport;
    }
}
