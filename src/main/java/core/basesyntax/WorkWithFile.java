package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int countSupply = 0;
        int countBuy = 0;

        try {
            List<String> allLines = Files.readAllLines(new File(fromFileName).toPath());

            for (String line : allLines) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue; // Пропустить строку, если нет двух частей
                }

                String operation = parts[0].trim();
                String valueStr = parts[1].trim();

                int value;
                try {
                    value = Integer.parseInt(valueStr);
                } catch (NumberFormatException e) {
                    continue; // Пропустить строку, если значение не целое число
                }

                if ("supply".equals(operation)) {
                    countSupply += value;
                } else if ("buy".equals(operation)) {
                    countBuy += value;
                } // Игнорировать другие операции
            }
        } catch (IOException e) {
            throw new RuntimeException("It is not able to read file!", e);
        }

        String result = "supply," + countSupply + System.lineSeparator()
                + "buy," + countBuy + System.lineSeparator()
                + "result," + (countSupply - countBuy);

        try {
            Files.write(new File(toFileName).toPath(), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("It is not able to write to file!", e);
        }
    }
}
