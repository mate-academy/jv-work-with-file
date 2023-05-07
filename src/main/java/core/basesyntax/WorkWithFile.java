package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;
        File fileRead = new File(fromFileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileRead));
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(",");
                value = reader.readLine();
            }
            String resultBuilder = builder.toString();
            String[] data = resultBuilder.split(",");
            for (int i = 0; i < data.length; i++) {
                if (data[i].equals("supply")) {
                    sumSupply = sumSupply + Integer.parseInt(data[i + 1]);
                }
                if (data[i].equals("buy")) {
                    sumBuy = sumBuy + Integer.parseInt(data[i + 1]);
                }
            }
            int result = sumSupply - sumBuy;
            String resultString = "supply," + sumSupply + System.lineSeparator()
                    + "buy," + sumBuy + System.lineSeparator()
                    + "result," + result;
            File fileWrite = new File(toFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileWrite));
            writer.write(resultString);
            reader.close();
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
