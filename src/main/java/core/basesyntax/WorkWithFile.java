package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File readable = new File(fromFileName);
        File writable = new File(toFileName);
        StringBuilder builder = new StringBuilder();
        int supplyValue = 0;
        int buyValue = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readable));
            String value = reader.readLine();
            while (value != null) {
                if (value.contains("supply")) {
                    supplyValue += Integer.parseInt(value.substring(value.indexOf(',') + 1));
                }
                if (value.contains("buy")) {
                    buyValue += Integer.parseInt(value.substring(value.indexOf(',') + 1));
                }
                value = reader.readLine();
            }
            builder.append("supply,").append(supplyValue).append(System.lineSeparator())
                    .append("buy,").append(buyValue).append(System.lineSeparator())
                    .append("result,").append(supplyValue - buyValue);

        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writable));) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file", e);
        }

    }
}
