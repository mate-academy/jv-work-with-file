package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY_NAME = "supply";
    static final String BUY_NAME = "buy";
    static final String RESULT_NAME = "result";
    static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        String operation;
        int amount;
        String[] linePart;
        StringBuilder report = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();

            while (value != null) {
                linePart = value.split(COMMA);
                if (linePart.length == 2) {
                    operation = linePart[0].trim();
                    amount = Integer.parseInt(linePart[1].trim());
                    if (operation.equals(SUPPLY_NAME)) {
                        supplyAmount += amount;
                    } else if (operation.equals(BUY_NAME)) {
                        buyAmount += amount;
                    } else {
                        throw new RuntimeException("Operation does not exist");
                    }
                } else {
                    throw new RuntimeException("Line incorrect");
                }

                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
        report.append(SUPPLY_NAME + COMMA).append(supplyAmount).append(System.lineSeparator())
                .append(BUY_NAME + COMMA).append(buyAmount).append(System.lineSeparator())
                .append(RESULT_NAME + COMMA).append(supplyAmount - buyAmount);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.append(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write into file: " + toFileName,e);
        }

    }
}
