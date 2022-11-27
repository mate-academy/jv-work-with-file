package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(' ');
                value = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String[]data = stringBuilder.toString().split(" ");
        int sumSupply = 0;
        int sumBuy = 0;
        for (String partData : data) {
            String[] element = partData.split(",");
            if (element[0].equals("supply")) {
                sumSupply += Integer.parseInt(element[1]);
            } else {
                sumBuy += Integer.parseInt(element[1]);
            }
        }
        String[] report = {("supply," + sumSupply), ("buy," + sumBuy),
                ("result," + (sumSupply - sumBuy))};
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
            for (String line : report) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }

    }
}
