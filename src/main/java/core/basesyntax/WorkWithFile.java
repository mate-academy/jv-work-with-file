package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operation.equals("supply")) { // Виправлено назву
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }

        int result = supplySum - buySum;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplySum + System.lineSeparator());
            writer.write("buy," + buySum + System.lineSeparator());
            writer.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        String inputFile = "apple.csv";
        String outputFile = "output.csv";

        workWithFile.getStatistic(inputFile, outputFile);
        System.out.println("Файл оброблено успішно!");
    }
}
