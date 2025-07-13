import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SupplyCalculator {

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue; // Пропустити некоректний рядок
                }

                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals("постачання")) {
                    supplyTotal += amount;
                } else if (operation.equals("купівля")) {
                    buyTotal += amount;
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Помилка під час читання файлу: " + fromFileName, e);
        }

        int result = supplyTotal - buyTotal;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("постачання," + supplyTotal + System.lineSeparator());
            writer.write("купівля," + buyTotal + System.lineSeparator());
            writer.write("результат," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Помилка під час запису у файл: " + toFileName, e);
        }
    }
}

