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
        String split = ",";

        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = fileReader.readLine();
            while (value != null) {
                String[] valueParts = value.split(split);
                if (valueParts[0].equalsIgnoreCase("buy")) {
                    totalBuy += Integer.parseInt(valueParts[1]);
                } else if (valueParts[0].equalsIgnoreCase("supply")) {
                    totalSupply += Integer.parseInt(valueParts[1]);
                }
                value = fileReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }

        int result = totalSupply - totalBuy;

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("supply,").append(totalSupply).append(System.lineSeparator());
            stringBuilder.append("buy,").append(totalBuy).append(System.lineSeparator());
            stringBuilder.append("result,").append(result).append(System.lineSeparator());
            fileWriter.write(String.valueOf(stringBuilder));

        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + toFileName, e);
        }
    }
}
