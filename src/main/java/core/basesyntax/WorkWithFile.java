package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        int supplyTotal = 0;
        int buyTotal = 0;
        int resultTotal;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                String[] lineParts = value.split(",");
                if (lineParts[0].equals("buy")) {
                    try {
                        buyTotal += Integer.parseInt(lineParts[1]);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Wrong data type", e);
                    }
                }
                if (lineParts[0].equals("supply")) {
                    try {
                        supplyTotal += Integer.parseInt(lineParts[1]);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Wrong data type", e);
                    }
                }

                value = reader.readLine();

            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file", e);
        }

        resultTotal = supplyTotal - buyTotal;

        String supplyValue = String.valueOf(supplyTotal);
        String buyValue = String.valueOf(buyTotal);
        String resultValue = String.valueOf(resultTotal);

        StringBuilder supply = new StringBuilder();
        supply
                .append("supply,")
                .append(supplyValue);

        StringBuilder buy = new StringBuilder();
        buy
                .append("buy,")
                .append(buyValue);

        StringBuilder result = new StringBuilder();
        result
                .append("result,")
                .append(resultValue);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(supply.toString());
            writer.newLine();
            writer.write(buy.toString());
            writer.newLine();
            writer.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
