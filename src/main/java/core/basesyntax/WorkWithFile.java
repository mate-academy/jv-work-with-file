package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int AMOUNT_PART = 1;
    public static final int OPERATION_TYPE_PART = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                String operationType = data[OPERATION_TYPE_PART].trim();
                int amount = Integer.parseInt(data[AMOUNT_PART].trim());

                if ("supply".equals(operationType)) {
                    totalSupply += amount;
                } else if ("buy".equals(operationType)) {
                    totalBuy += amount;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }

        writeReport(toFileName, totalSupply, totalBuy);
    }

    private void writeReport(String toFileName, int totalSupply, int totalBuy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + totalSupply);
            writer.newLine();
            writer.write("buy," + totalBuy);
            writer.newLine();
            writer.write("result," + (totalSupply - totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file", e);
        }
    }
}
