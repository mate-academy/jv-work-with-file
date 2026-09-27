package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(";");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        String[] datas = builder.toString().split(";");
        int totalBuy = 0;
        int totalSupply = 0;
        for (String data : datas) {
            String[] dataLine = data.split(",");
            if (dataLine[0].equals("buy")) {
                totalBuy += Integer.parseInt(dataLine[1]);
            }
            if (dataLine[0].equals("supply")) {
                totalSupply += Integer.parseInt(dataLine[1]);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + totalSupply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + totalBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + (totalSupply - totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }

    }
}
