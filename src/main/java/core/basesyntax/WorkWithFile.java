package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            StringBuilder builder = new StringBuilder();
            int sumSupply = 0;
            int sumBuy = 0;
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                String[] lines = string.split("\n");
                for (String line : lines) {
                    String[] arr = line.split(",");

                    String type = arr[0].trim();
                    int number = Integer.parseInt(arr[1].trim());

                    if (type.equals("supply")) {
                        sumSupply += number;
                        break;
                    } else if (type.equals("buy")) {
                        sumBuy += number;
                        break;
                    }
                }
            }

            builder.setLength(0);
            builder.append("supply,").append(sumSupply);
            builder.append(System.lineSeparator()).append("buy,").append(sumBuy);
            int result = sumSupply - sumBuy;
            builder.append(System.lineSeparator()).append("result,").append(result);

            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error : file not found ", e);
        }
    }
}
