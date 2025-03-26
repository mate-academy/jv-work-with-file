package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumBuy = 0;
        int sumSupply = 0;
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String type = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    if ("buy".equals(type)) {
                        sumBuy += value;
                    } else if ("supply".equals(type)) {
                        sumSupply += value;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        writeReport(toFileName, sumSupply, sumBuy);
    }

    private void writeReport(String toFileName, int sumSupply, int sumBuy) {
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write("supply," + sumSupply);
            writer.newLine();
            writer.write("buy," + sumBuy);
            writer.newLine();
            writer.write("result," + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
