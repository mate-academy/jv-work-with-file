package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;
        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                if ((char)value == (char)'\n') {
                    String[] record = stringBuilder.toString().split(",");
                    if (record[0].equals("buy")) {
                        buy += Integer.parseInt(record[1]);
                    }
                    if (record[0].equals("supply")) {
                        supply += Integer.parseInt(record[1]);
                    }
                    stringBuilder.setLength(0);
                } else {
                    stringBuilder.append((char) value);
                }
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file",e);
        }
        result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Can't open file",e);
        }
    }
}
