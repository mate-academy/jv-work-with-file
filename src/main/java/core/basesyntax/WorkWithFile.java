package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(" ");
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file" + e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
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
            } else {
                sumSupply += Integer.parseInt(info[1]);
            }
        }
        int result = sumSupply - sumBuy;
        builder1.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result);
        return builder1.toString();
    }

}
