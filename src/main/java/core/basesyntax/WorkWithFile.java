package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        final File file = new File(fromFileName);
        final StringBuilder builder = new StringBuilder();
        final String [] array;
        int buy = 0;
        int supply = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value);
                builder.append("\n");
            }
        } catch (IOException e) {
            System.out.println("file read error " + e);
        }
        array = builder.toString().split("\n");

        for (String s : array) {
            String[] data = s.split(",");
            buy += data[0].equals("buy") ? Integer.parseInt(data[1]) : 0;
            supply += data[0].equals("supply") ? Integer.parseInt(data[1]) : 0;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write("supply,");
            writer.write(String.valueOf(supply));
            writer.write(System.lineSeparator());
            writer.write("buy,");
            writer.write(String.valueOf(buy));
            writer.write(System.lineSeparator());
            writer.write("result,");
            writer.write(String.valueOf(supply - buy));
        } catch (IOException e) {
            System.out.println("file write error " + e);
        }
    }
}
