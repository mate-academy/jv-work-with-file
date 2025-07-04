package core.basesyntax;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
            reader.close();
            String[] arrayList = builder.toString().split(System.lineSeparator());
            for (String line : arrayList) {
                String[] part = line.split(",");
                if (part[0].equals("supply")) {
                    supply += Integer.parseInt(part[1]);
                } else if (part[0].equals("buy")) {
                    buy += Integer.parseInt(part[1]);
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
                writer.write("supply," + supply);
                writer.newLine();
                writer.write("buy," + buy);
                writer.newLine();
                writer.write("result," + (supply - buy));
            } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }
}
