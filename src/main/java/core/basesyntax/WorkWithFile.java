package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String resultData = getDataFromFile(fromFileName);
        writeData(resultData, toFileName);
    }

    private String getDataFromFile(String fileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                if (line.split(",")[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(line.split(",")[1]);
                } else if (line.split(",")[0].equals("buy")) {
                    buyAmount += Integer.parseInt(line.split(",")[1]);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data.", e);
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("supply").append(",").append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy").append(",").append(buyAmount)
                .append(System.lineSeparator())
                .append("result").append(",").append(supplyAmount - buyAmount);

        return stringBuilder.toString();
    }

    private void writeData(String data, String fileName) {
        for (String dataRow : data.split(System.lineSeparator())) {
            try (BufferedWriter bufferedWriter =
                         new BufferedWriter(new FileWriter(fileName, true))) {
                bufferedWriter.write(dataRow);
                bufferedWriter.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException("Can't write data.", e);

            }
        }
    }
}
