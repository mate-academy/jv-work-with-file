package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {

        int supply = 0;
        int buy = 0;
        int result = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
            String[] smth = builder.toString().split(System.lineSeparator());
            for (int i = 0; i < smth.length; i++) {
                String[][] data = new String[][] {smth[i].split(",")};
                if (data[0][0].equals("supply")) {
                    supply += Integer.parseInt(data[0][1]);
                }
                if (data[0][0].equals("buy")) {
                    buy += Integer.parseInt(data[0][1]);
                }
            }
            result = supply - buy;
            writeResultToFile(toFileName, supply, buy, result);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeResultToFile(String toFileName, int supply, int buy, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply);
            writer.newLine();
            writer.write("buy," + buy);
            writer.newLine();
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
