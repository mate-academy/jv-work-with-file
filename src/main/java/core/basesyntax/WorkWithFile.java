package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] parse = line.split(",");
                String name = parse[0];
                int value = Integer.parseInt(parse[1]);
                if (name.equals("supply")) {
                    supply += value;
                } else {
                    buy += value;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file", e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.append("supply,").append(String.valueOf(supply)).append("\n");
            writer.append("buy,").append(String.valueOf(buy)).append("\n");
            writer.append("result,").append(String.valueOf(supply - buy)).append("\n");
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }

    }
}
