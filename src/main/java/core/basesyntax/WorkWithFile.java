package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    private static final String DESIRED_LETTER = "w";

    public String[] readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> dynamicArray = new ArrayList<>();
            String value = reader.readLine();

            while (value != null) {
                stringBuilder.append(" ").append(value);
                value = reader.readLine();
            }

            String[] split = stringBuilder.toString().split("\\W+");
            for (String result : split) {
                if (result.toLowerCase().startsWith(DESIRED_LETTER)) {
                    dynamicArray.add(result.toLowerCase());
                }
            }

            return dynamicArray.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred: " + e.getMessage(), e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fromFileName))
        ) {
            int totalSupply = 0;
            int totalBuy = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length == 2) {
                    String operation = parts[0];
                    int amount = Integer.parseInt(parts[1]);

                    if ("supply".equals(operation)) {
                        totalSupply += amount;
                    } else if ("buy".equals(operation)) {
                        totalBuy += amount;
                    }
                }
            }

            int result = totalSupply - totalBuy;

            String report = "supply," + totalSupply + System.lineSeparator()
                    + "buy," + totalBuy + System.lineSeparator() + "result," + result;

            writeToFile(report, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred: " + e.getMessage(), e);
        }
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't create or write data to the file " + toFileName, e);
        }
    }

    public static void main(String[] args) {
        String[] fileNames = {"apple.csv", "grape.csv", "orange.csv", "banana.csv"};
        WorkWithFile fileWorker = new WorkWithFile();

        for (String fileName : fileNames) {
            String[] resultArray = fileWorker.readFromFile(fileName);

            for (String result : resultArray) {
                System.out.println(result);
            }
        }
    }
}
