package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            FileReader fileReader = new FileReader(fromFileName);
            BufferedReader bf = new BufferedReader(fileReader);
            String line;
            int totalBuy = 0;
            int totalSupply = 0;
            while ((line = bf.readLine()) != null) {
                String[] str = line.split(",");
                if (str[0].equals("supply")) {
                    totalSupply = totalSupply + Integer.valueOf(str[1]);
                }
                if (str[0].equals("buy")) {
                    totalBuy = totalBuy + Integer.valueOf(str[1]);
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write("supply," + totalSupply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + totalBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (totalSupply - totalBuy));
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
