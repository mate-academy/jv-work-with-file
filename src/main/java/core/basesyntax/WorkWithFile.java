package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringFromFile = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringFromFile.append(value).append(",");
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        String[] information = stringFromFile.toString().split(",");
        StringBuilder result = new StringBuilder();
        int sumOfBuy = 0;
        int sumOfSupply = 0;
        for (int i = 0; i < information.length - 1; i += 2) {
            sumOfBuy += information[i].equals("buy")
                    ? Integer.parseInt(information[i + 1]) : 0;
            sumOfSupply += information[i].equals("supply")
                    ? Integer.parseInt(information[i + 1]) : 0;
        }
        result.append("supply,")
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append("result,")
                .append(sumOfSupply - sumOfBuy);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + toFileName, e);
        }
    }
}
