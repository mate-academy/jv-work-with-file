package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        int buy = 0;
        int supply = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                String[] string = value.split(",");
                if (string[0] != null && string[0].equals("buy")) {
                    buy += Integer.parseInt(string[1]);
                }
                if (string[0] != null && string[0].equals("supply")) {
                    supply += Integer.parseInt(string[1]);
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            stringBuilder
                    .append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
