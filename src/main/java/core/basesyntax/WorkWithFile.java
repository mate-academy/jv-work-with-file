package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyNumber = 0;
        int buyNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] array = line.split(",");
                if (array.length < 2) {
                    continue;
                }
                if (array[0].equals("supply")) {
                    supplyNumber = supplyNumber + Integer.parseInt(array[1]);
                }
                if (array[0].equals("buy")) {
                    buyNumber = buyNumber + Integer.parseInt(array[1]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find a file", e);
        }

        int result = supplyNumber - buyNumber;
        String[][] arrayResult = {{"supply", "buy", "result"},
                {String.valueOf(supplyNumber), String.valueOf(buyNumber), String.valueOf(result)}};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            for (int i = 0; i < arrayResult[0].length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                writer.write(String.valueOf(stringBuilder.append(arrayResult[0][i])
                        .append(",")
                        .append(arrayResult[1][i])
                        .append(System.lineSeparator())));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find a file", e);
        }
    }
}
