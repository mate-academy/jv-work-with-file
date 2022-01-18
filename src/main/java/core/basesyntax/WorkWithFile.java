package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(";");
                value = bufferedReader.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't reader file " + fromFileName, e);
        }
        String getFromFile = stringBuilder.toString();
        String [] arrayResults = getFromFile.split(";");

        int sumSupply = 0;
        int sumBuy = 0;
        String supply = "supply";
        String buy = "buy";

        for (String arrayResult: arrayResults) {
            String [] arrays = arrayResult.split(",");
            if (arrays[0].equals(supply)) {
                sumSupply += Integer.parseInt(arrays[1]);
            } else if (arrays[0].equals(buy)) {
                sumBuy += Integer.parseInt(arrays[1]);
            }
        }
        int getResult = sumSupply - sumBuy;
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(supply).append(",").append(sumSupply).append(System.lineSeparator());
        resultBuilder.append(buy).append(",").append(sumBuy).append(System.lineSeparator());
        resultBuilder.append("result").append(",").append(getResult).append(System.lineSeparator());
        String result = resultBuilder.toString();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't writer file " + toFileName, e);
        }
    }
}

