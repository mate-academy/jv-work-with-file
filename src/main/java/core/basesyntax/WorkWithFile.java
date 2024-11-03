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

        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String operationType = parts[0].trim();
                int amount = Integer.parseInt(parts[1].trim());

                if (operationType.equals("supply")) {
                    totalSupply += amount;
                } else if (operationType.equals("buy")) {
                    totalBuy += amount;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int result = totalSupply - totalBuy;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + totalSupply);
            bw.newLine();
            bw.write("buy," + totalBuy);
            bw.newLine();
            bw.write("result," + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("input.csv", "output.csv");
    }
}
