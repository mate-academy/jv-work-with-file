package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalBuy = 0;
        int totalSupply = 0;
        int result = 0;
        int value = 0;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            line = bufferedReader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                value = Integer.parseInt(parts[1]);

                if (parts[0].equals("buy")) {
                    totalBuy += value;
                } else if (parts[0].equals("supply")) {
                    totalSupply += value;
                }
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can`t read the file!");
        }
        result = totalSupply - totalBuy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply + System.lineSeparator());
            writer.write("buy," + totalBuy + System.lineSeparator());
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to the file!");
        }
    }
}
