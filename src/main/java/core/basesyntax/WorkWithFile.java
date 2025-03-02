package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split((","));
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
        ArrayList<String> supply = new ArrayList<>();
        supply.add("Item 0");
        supply.add("Item 3");

        System.out.println("Supply equals " + supply);

        ArrayList<Integer> buy = new ArrayList<>();
        buy.add(Integer.valueOf("Item 1"));
        buy.add(Integer.valueOf("Item 2"));
        buy.add(Integer.valueOf("Item 4"));

        System.out.println("Buy equals " + buy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            writer.write(String.join(",", headers));
            writer.newLine();
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't write  " + toFileName, e);
        }
    }
}