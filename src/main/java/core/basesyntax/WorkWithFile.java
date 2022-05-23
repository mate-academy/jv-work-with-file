package core.basesyntax;

import java.io.*;
import java.util.Arrays;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            builder.append(data).append(" ");
        }catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file" + e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))){
            String allData = getReport(builder.toString());
            writer.write(allData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + e);
        }
    }

    public String getReport(String allData) {
        StringBuilder builder1 = new StringBuilder();
        String [] arr = allData.split(" ");
        int sumBuy = 0;
        int sumSupply = 0;
        for (String ar : arr) {
            String [] info = ar.split(",");
            if (info[0].equals("buy")) {
              sumBuy += Integer.parseInt(info[1]);
            }
            sumSupply += Integer.parseInt(info[1]);
        }
        int result = sumBuy - sumSupply;
        builder1
        return builder1.toString();
    }

}
