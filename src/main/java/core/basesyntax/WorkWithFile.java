package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }

            String supplyAndBuyToString = stringBuilder.toString();

            String[] supplyAndBuy = supplyAndBuyToString.split(" ");

            StringBuilder supplyBuilder = new StringBuilder();
            StringBuilder buyBuilder = new StringBuilder();

            for (int i = 0; i < supplyAndBuy.length; i++) {
                switch (supplyAndBuy[i].charAt(0)) {
                    case 's':
                        supplyBuilder.append(supplyAndBuy[i]).append(" ");
                        break;
                    default:
                        buyBuilder.append(supplyAndBuy[i]).append(" ");
                        break;

                }
            }
            String stringSupply = supplyBuilder.toString();
            String stringBuy = buyBuilder.toString();
            String[] arraySupply = stringSupply.split("\\D+");
            String[] arrayBuy = stringBuy.split("\\D+");
            int[] supplyNumbers = new int[arraySupply.length - 1];
            int[] buyNumbers = new int[arrayBuy.length - 1];
            for (int i = 1; i < arraySupply.length; i++) {
                int local = Integer.parseInt(arraySupply[i]);
                supplyNumbers[i - 1] += local;
            }
            for (int i = 1; i < arrayBuy.length; i++) {
                int local = Integer.parseInt(arrayBuy[i]);
                buyNumbers[i - 1] += local;
            }

            int sumSupply = 0;
            int sumBuy = 0;
            int result = 0;

            for (int i = 0; i < supplyNumbers.length; i++) {
                sumSupply += supplyNumbers[i];
            }
            for (int i = 0; i < buyNumbers.length; i++) {
                sumBuy += buyNumbers[i];
            }

            result = sumSupply - sumBuy;

            StringBuilder data = new StringBuilder();
            data.append("supply,").append(sumSupply).append(System.lineSeparator())
                    .append("buy,").append(sumBuy).append(System.lineSeparator())
                    .append("result,").append(result);

            String completedTextFromFileWriter = data.toString();

            File finalFile = new File(toFileName);

            BufferedWriter writerToFinalFile = new BufferedWriter(new FileWriter(finalFile, true));
            writerToFinalFile.write(completedTextFromFileWriter);
            writerToFinalFile.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file or can't write data to file", e);
        }
    }
}
