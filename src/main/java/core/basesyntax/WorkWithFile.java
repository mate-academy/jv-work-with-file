package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_NAME = 0;
    private static final int INDEX_OF_NUM = 1;

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

    private String getReport(String allData) {
        StringBuilder builder1 = new StringBuilder();
        String [] arr = allData.split(" ");
        int sumBuy = 0;
        int sumSupply = 0;
        for (String ar : arr) {
            String [] info = ar.split(",");
            if (info[INDEX_OF_NAME].equals("buy")) {
                sumBuy += Integer.parseInt(info[INDEX_OF_NUM]);
            } else {
                sumSupply += Integer.parseInt(info[INDEX_OF_NUM]);
            }
        }
        int result = sumSupply - sumBuy;
        builder1.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result);
        return builder1.toString();
    }

}
