package core.basesyntax;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        File file1 = new File(toFileName);

        if (file.length() == 0) {
            System.out.println("File is empty");
            return;
        }

        int amountSupply = 0;
        int amountBuy = 0;
        int result;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();

            String value;
            while ((value = reader.readLine()) != null) {
                String[] parts = value.split(",");
                if (parts[0].equals("supply")) {
                    amountSupply += Integer.parseInt(parts[1]);
                } else if (parts[0].equals("buy")) {
                    amountBuy += Integer.parseInt(parts[1]);
                }
            }
            result = amountSupply - amountBuy;

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String report = "supply," + amountSupply + "\n"
                + "buy," + amountBuy + "\n"
                + "result," + result;

        try (FileWriter writer = new FileWriter(file1)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
