package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            int resultSupply = 0;
            int resultBuy = 0;
            int result;

            String value = reader.readLine();
            while (value != null) {
                String[] data = value.split(",");

                if (data.length == 2) {
                    if (data[0].equals("supply")) {
                        resultSupply += Integer.parseInt(data[1]);
                    } else if (data[0].equals("buy")) {
                        resultBuy += Integer.parseInt(data[1]);
                    }
                }

                value = reader.readLine();
            }

            result = resultSupply - resultBuy;

            bufferedWriter.write("supply," + resultSupply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + resultBuy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);

        } catch (IOException e) {
            throw new RuntimeException("Can't work with file.", e);
        }
    }
}
