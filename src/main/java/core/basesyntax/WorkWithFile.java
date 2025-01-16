package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private Map<String, Integer> value = new HashMap<>();

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] arr = line.split(",");
                setValue(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File endFile = new File(toFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(endFile))) {

            writer.write(String.format("%s,%d%n", "supply", value.get("supply")));
            writer.write(String.format("%s,%d%n", "buy", value.get("buy")));
            int result = value.get("supply") - value.get("buy");
            writer.write(String.format("%s,%d", "result", result));

            value.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setValue(String[] arr) {
        if (value.containsKey(arr[0])) {
            int amount = value.get(arr[0]) + Integer.valueOf(arr[1]);
            value.put(arr[0], amount);
        } else {
            value.put(arr[0], Integer.valueOf(arr[1]));
        }
    }

    public static void main(String[] args) {
        WorkWithFile work = new WorkWithFile();
        work.getStatistic("banana.csv", "1.txt");
    }
}
