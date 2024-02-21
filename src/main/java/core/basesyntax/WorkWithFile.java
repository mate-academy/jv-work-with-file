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
        String[] parts = new String[] {};
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                if (parts.length == 2) {
                    String operation = parts[0];
                    int amount = Integer.parseInt(parts[1]);
                    if ("supply".equals(operation)) {
                        totalSupply += amount;
                    }
                    if ("buy".equals(operation)) {
                        totalBuy += amount;
                    }
                }
            }
            bufferedWriter.write("supply," + totalSupply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + totalBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (totalSupply - totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write info");
        }
    }

    public static void main(String[] args) {
        WorkWithFile fileWorker = new WorkWithFile();
        fileWorker.getStatistic("input.csv", "output.csv");
    }
}
