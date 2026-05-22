package core.basesyntax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (Scanner scanner = new Scanner(new File(fromFileName))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                String operation = data[0];
                int amount = Integer.parseInt(data[1]);

                if (operation.equals("supply")) {
                    totalSupply += amount;
                } else if (operation.equals("buy")) {
                    totalBuy += amount;
                }
            }

            int result = totalSupply - totalBuy;

            try (FileWriter writer = new FileWriter(toFileName)) {
                writer.write("supply," + totalSupply + System.lineSeparator());
                writer.write("buy," + totalBuy + System.lineSeparator());
                writer.write("result," + result);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
// All your comments are meaningless!
// You keep referring to a checklist that doesn’t exist, and all your links lead to someone else’s pull requests instead of the checklist!
