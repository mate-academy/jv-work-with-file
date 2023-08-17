package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int REQUIRED_PARTS_LENGTH = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int totalSupply = 0;
            int totalBuy = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == REQUIRED_PARTS_LENGTH) {
                    String operation = parts[OPERATION_INDEX];
                    int amount = Integer.parseInt(parts[AMOUNT_INDEX]);

                    if (SUPPLY.equals(operation)) {
                        totalSupply += amount;
                    } else if (BUY.equals(operation)) {
                        totalBuy += amount;
                    }
                }
            }

            int result = totalSupply - totalBuy;

            String report = "supply," + totalSupply + System.lineSeparator()
                    + "buy," + totalBuy + System.lineSeparator() + "result," + result;

            writeToFile(report, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Произошла ошибка: " + e.getMessage(), e);
        }
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Не удается создать или записать данные в файл "
                    + toFileName, e);
        }
    }
}
