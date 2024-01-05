package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_CHARACTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();

            while (value != null) {
                builder.append(value).append(SPLIT_CHARACTER);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        String[] data = builder.toString().split(SPLIT_CHARACTER);
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supply += Integer.valueOf(data[i + 1]);
            }
            if (data[i].equals("buy")) {
                buy += Integer.valueOf(data[i + 1]);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write("supply," + supply + System.lineSeparator()
                          + "buy," + buy + System.lineSeparator()
                          + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
