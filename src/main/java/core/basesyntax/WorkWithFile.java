package core.basesyntax;

import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (type.equals("supply")) {
                    totalSupply += amount;
                } else if (type.equals("buy")) {
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
            System.out.println("An error occurred: " + e);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in the input file: " + e);
        }
    }
}
