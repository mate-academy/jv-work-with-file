package core.basesyntax;

import java.io.*;
import java.util.Arrays;

public class WorkWithFile {
    public static void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split((","));
                System.out.println(Arrays.toString(values));
            }
        } catch (IOException e) {
            {
                throw new RuntimeException("Can't read data from the file " + fromFileName, e);
            }
        }

        String[] headers = {"operation time", "amount"};
        String[][] data = {
                {"supply", "30"},
                {"buy", "10"},
                {"buy", "13"},
                {"supply", "17"},
                {"buy", "10"}
        };
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write(String.join(",", headers));
            writer.newLine();
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("CSV file written successfully");
        } catch (IOException e) {
            throw new RuntimeException("Can't write  " + toFileName, e);
        }
      }
    }