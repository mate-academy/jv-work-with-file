package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String newLine = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] arrayString = builder.toString().split(" ");
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String s : arrayString) {
            String[] data = s.split(",");
            if (data[0].equals("supply")) {
                supplyAmount += Integer.parseInt(data[1]);
            } else if (data[0].equals("buy")) {
                buyAmount += Integer.parseInt(data[1]);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAmount + newLine);
            writer.write("buy," + buyAmount + newLine);
            writer.write("result," + (supplyAmount - buyAmount));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
