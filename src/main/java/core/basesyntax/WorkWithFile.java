package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        String[] data;

        // reading from file
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
            data = builder.toString().split("[,\\n]");
        } catch (IOException e) {
            throw new RuntimeException("Can't open a file", e);
        }

        //counting data
        String[] countedData = countData(data);

        //writing to file
        File toFile = new File(toFileName);
        for (String count : countedData) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
                writer.write(count);
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        }

    }

    private String[] countData(String[] data) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals("supply")) {
                supply += Integer.parseInt(data[i + 1]);
            } else if (data[i].equals("buy")) {
                buy += Integer.parseInt(data[i + 1]);
            }
        }
        int result = supply - buy;
        String[] countedData = {"supply,", String.valueOf(supply), System.lineSeparator(),
                                "buy,", String.valueOf(buy), System.lineSeparator(),
                                "result,", String.valueOf(result)};
        return countedData;
    }

}
