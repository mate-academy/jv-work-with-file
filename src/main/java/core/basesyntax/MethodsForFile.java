package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MethodsForFile {
    public String getReadFile(String fromFileName) {
        File file = new File(fromFileName);
        int sumSupply = 0;
        int sumBuy = 0;
        final int Result_Numbers;

        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(" ");
                value = bufferedReader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file or can't write data to file", e);
        }
        String supplyAndBuyToString = stringBuilder.toString();

        String[] supplyAndBuy = supplyAndBuyToString.split(" ");

        StringBuilder supplyBuilder = new StringBuilder();
        StringBuilder buyBuilder = new StringBuilder();

        for (String s : supplyAndBuy) {
            if (s.charAt(0) == 's') {
                supplyBuilder.append(s).append(" ");
            } else {
                buyBuilder.append(s).append(" ");
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

        for (int supplyNumber : supplyNumbers) {
            sumSupply += supplyNumber;
        }
        for (int buyNumber : buyNumbers) {
            sumBuy += buyNumber;
        }

        Result_Numbers = sumSupply - sumBuy;

        String completedTextFromFileWriter = "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + Result_Numbers;
        return completedTextFromFileWriter;
    }

    public void getWriteFile(String toFileName, String completedText) {
        File finalFile = new File(toFileName);

        try {
            BufferedWriter writerToFinalFile = new BufferedWriter(new FileWriter(finalFile, true));
            writerToFinalFile.write(completedText);
            writerToFinalFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
