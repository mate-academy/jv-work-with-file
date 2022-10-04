package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private File file;
    private final StringBuilder builder = new StringBuilder();
    private int buy = 0;
    private int supply = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        file = new File(fromFileName);

        readFromFile();
        writeToFile(buy, supply, toFileName);
    }

    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value);
                builder.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("file read error " + e);
        }
        String[] array = builder.toString().split("\n");

        for (String s : array) {
            String[] data = s.split(",");
            this.buy += data[0].equals("buy") ? Integer.parseInt(data[1]) : 0;
            this.supply += data[0].equals("supply") ? Integer.parseInt(data[1]) : 0;
        }
    }

    public void writeToFile(int buy, int supply, String toFileName) {
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
            throw new RuntimeException("file write error " + e);
        }
    }
}
