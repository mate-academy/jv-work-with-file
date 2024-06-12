package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if ("supply".equals(operationType)) {
                    totalSupply += amount;
                } else if ("buy".equals(operationType)) {
                    totalBuy += amount;
                }
            }

            int result = totalSupply - totalBuy;

            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + result);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the input file: " + e.getMessage());
        }
    }
}
