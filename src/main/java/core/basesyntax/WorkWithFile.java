package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        // reading from file
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char)value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        // changing StringBuilder into String array
        String[] data = builder.toString().split("\n");

        //counting result
        int supply = 0;
        int buy = 0;

        for (String record : data) {
            String[] recordSplited = record.split(",");

            if (recordSplited[0].equals("supply")) {
                supply = supply + Integer.valueOf(recordSplited[1]);
            }

            if (recordSplited[0].equals("buy")) {
                buy = buy + Integer.valueOf(recordSplited[1]);
            }
        }

        int result = supply - buy;

        StringBuilder raport = new StringBuilder();
        raport.append("supply,").append(supply).append(System.lineSeparator());
        raport.append("buy,").append(buy).append(System.lineSeparator());
        raport.append("result,").append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(raport.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
