package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int[] amounts = getDataFromFile(fromFileName);
        String[] resultData = buildData(amounts[0], amounts[1], amounts[2]);
        writeData(resultData, toFileName);
    }

    private int[] getDataFromFile(String fileName) {
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

        int[] arrayWithAmounts = new int[3];
        arrayWithAmounts[0] = supplyAmount;
        arrayWithAmounts[1] = buyAmount;
        arrayWithAmounts[2] = supplyAmount - buyAmount;

        return arrayWithAmounts;
    }

    private String[] buildData(int amount1, int amount2, int amount3) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("supply").append(",").append(amount1).append(System.lineSeparator())
                .append("buy").append(",").append(amount2).append(System.lineSeparator())
                .append("result").append(",").append(amount3);

        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeData(String[] data, String fileName) {
        for (String dataRow : data) {
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
