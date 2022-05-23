package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        String[] statisticArray = getReport(readFile(fromFileName));
        for (String data : statisticArray) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
                writer.write(data);
                writer.newLine();
            } catch (IOException e1) {
                throw new RuntimeException("Can't write data to the file" + toFileName, e1);
            }
        }
    }

    public String readFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
    }

    public String[] getReport(String dataString) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] dataArray = dataString.split(" ");
        for (String data : dataArray) {
            int index = data.indexOf(",");
            String string = data.substring(0,index);
            int dataSum = Integer.parseInt(data.substring(index + 1));
            if (string.equals(SUPPLY)) {
                sumSupply = sumSupply + dataSum;
            } else {
                sumBuy = sumBuy + dataSum;
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(sumSupply).append(" ").append(BUY)
                .append(",").append(sumBuy).append(" ").append(RESULT).append(",").append(result);
        String stringReport = builder.toString();
        return stringReport.split(" ");
    }
}

