package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int valueSupply = 0;
        int valueBuy = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                String[] split = value.split(",");
                value = reader.readLine();
                if (split[0].equals("supply")) {
                    valueSupply += Integer.parseInt(split[1]);
                } else if (split[0].equals("buy")) {
                    valueBuy += Integer.parseInt(split[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        String supply = "supply," + String.valueOf(valueSupply);
        String buy = "buy," + String.valueOf(valueBuy);
        String result = "result," + String.valueOf(valueSupply - valueBuy);
        String[] dataToFile = {supply, buy, result};
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));) {
            for (String data : dataToFile) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}

