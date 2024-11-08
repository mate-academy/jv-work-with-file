package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int OPERATION_POSITION = 0;
    static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCount = 0;
        int buyCount = 0;
        File fromFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        String[] lines = builder.toString().split(System.lineSeparator());
        for (String line : lines) {
            String[] lineSplit = line.split(",");
            if (lineSplit[OPERATION_POSITION].equals("supply")) {
                try {
                    supplyCount += Integer.parseInt(lineSplit[AMOUNT_POSITION]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number", e);
                }
            }
            if (lineSplit[OPERATION_POSITION].equals("buy")) {
                try {
                    buyCount += Integer.parseInt(lineSplit[AMOUNT_POSITION]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number", e);
                }
            }
        }
        builder = new StringBuilder();
        builder.append("supply,").append(supplyCount).append(System.lineSeparator());
        builder.append("buy,").append(buyCount).append(System.lineSeparator());
        builder.append("result,").append(supplyCount - buyCount);
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }

    }
}
