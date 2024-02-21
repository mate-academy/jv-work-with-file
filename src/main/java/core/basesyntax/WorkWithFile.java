package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        int totalResult = 0;
        File incomingFile = new File(fromFileName);
        File outgoingFile = new File(toFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(incomingFile))) {
            String i = reader.readLine();
            while (i != null) {
                String[] array = i.split(",");
                String item = array[0].trim();
                int summ = Integer.parseInt(array[1].trim());
                if (item.equals("supply")) {
                    totalSupply += summ;
                } else if (item.equals("buy")) {
                    totalBuy += summ;
                }
                i = reader.readLine();
            }
            totalResult = totalSupply - totalBuy;
        } catch (IOException b) {
            throw new RuntimeException(b.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outgoingFile))) {
            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + totalResult);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file" + e.getMessage());
        }
    }
}
