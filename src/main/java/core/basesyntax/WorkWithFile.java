package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        // Використовуємо Map для зберігання проміжних сум: [тип операції] -> [сума]
        Map<String, Integer> operationSums = new HashMap<>();
        operationSums.put(OPERATION_SUPPLY, 0);
        operationSums.put(OPERATION_BUY, 0);

        try {
            // 1. Читання всіх рядків із вхідного файлу
            Path fromFilePath = Paths.get(fromFileName);
            List<String> lines = Files.readAllLines(fromFilePath);

            // 2. Обробка та агрегація даних
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue; // Пропускаємо порожні рядки
                }

                String[] parts = line.split(DELIMITER);

                if (parts.length < 2) {
                    // Якщо формат рядка невірний, можна пропустити або кинути виняток.
                    // Для цієї задачі просто ігноруємо неповні рядки.
                    continue;
                }

                String operationType = parts[OPERATION_TYPE_INDEX].trim();
                int amount;

                try {
                    // Перетворення значення суми на число
                    amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
                } catch (NumberFormatException e) {
                    // Обробка нечислових значень
                    continue;
                }

                // Агрегація сум
                if (operationType.equals(OPERATION_SUPPLY)) {
                    int currentSupply = operationSums.get(OPERATION_SUPPLY);
                    operationSums.put(OPERATION_SUPPLY, currentSupply + amount);
                } else if (operationType.equals(OPERATION_BUY)) {
                    int currentBuy = operationSums.get(OPERATION_BUY);
                    operationSums.put(OPERATION_BUY, currentBuy + amount);
                }
            }

            // 3. Створення звіту
            int totalSupply = operationSums.get(OPERATION_SUPPLY);
            int totalBuy = operationSums.get(OPERATION_BUY);
            int result = totalSupply - totalBuy;

            String report = OPERATION_SUPPLY + DELIMITER + totalSupply + "\n"
                    + OPERATION_BUY + DELIMITER + totalBuy + "\n"
                    + "result" + DELIMITER + result + "\n";

            // 4. Запис звіту у вихідний файл
            Path toFilePath = Paths.get(toFileName);
            Files.writeString(toFilePath, report);

        } catch (IOException e) {
            // Обробка винятків, пов'язаних із доступом до файлів
            // Наприклад, кинути RuntimeException, щоб сигналізувати про збій
            throw new RuntimeException("Can't read or write file: " + e.getMessage(), e);
        }
    }
}
