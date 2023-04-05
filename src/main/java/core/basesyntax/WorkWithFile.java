package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NUMBER_0 = 0;

    private static final int NUMBER_1 = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String stringWithData;
        int supplyAmount = NUMBER_0;
        int buyAmount = NUMBER_0;
        int result = NUMBER_0;

        readFromFile(fromFileName, supplyAmount, buyAmount);


    }

    private void readFromFile(String fromFileName, int supplyAmount, int buyAmount) {
        String stringWithData;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((stringWithData = bufferedReader.readLine()) != null) {
                String[] arrayString = stringWithData.split("\\W");
                if (arrayString[NUMBER_0].equalsIgnoreCase("supply")) {
                    supplyAmount += Integer.parseInt(arrayString[NUMBER_1]);
                } else {
                    buyAmount += Integer.parseInt(arrayString[NUMBER_1]);
                }
            }
            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                    .append("buy,").append(buyAmount).append(System.lineSeparator())
                    .append("result,").append(supplyAmount - buyAmount);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter())


        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }
}
