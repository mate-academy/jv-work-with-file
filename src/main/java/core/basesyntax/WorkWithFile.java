package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        String supply = "supply";
        String buy = "buy";
        String result = "result";
        int supplyAmount = 0;
        int buyAmount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String line = reader.readLine();

            while (line != null) {
                if (line.split(",")[0].equals(supply)) {
                    supplyAmount += Integer.parseInt(line.split(",")[1]);
                } else if (line.split(",")[0].equals(buy)) {
                    buyAmount += Integer.parseInt(line.split(",")[1]);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data.", e);
        }

        int resultAmount = supplyAmount - buyAmount;

        stringBuilder.append(supply).append(",").append(supplyAmount).append(System.lineSeparator())
                .append(buy).append(",").append(buyAmount).append(System.lineSeparator())
                .append(result).append(",").append(resultAmount);

        String[] resultData = stringBuilder.toString().split(System.lineSeparator());

        BufferedWriter bufferedWriter = null;

        for (String dataRow : resultData) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true));
                bufferedWriter.write(dataRow);
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data.", e);
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException("Can't close BufferedWriter.", e);
                    }
                }
            }
        }
    }
}
