package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, createReport(readFile(fromFileName)));
    }

    private List<String> readFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                data.add(value);
                data.add(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return data;
    }

    private String createReport(List<String> data) {
        int sumSupply = 0;
        int sumBuy = 0;
        final int Result_Numbers;
        //
        StringBuilder supplyBuilder = new StringBuilder();
        StringBuilder buyBuilder = new StringBuilder();

        for (String datum : data) {
            if (datum.charAt(0) == 's') {
                supplyBuilder.append(datum).append(" ");
            } else {
                buyBuilder.append(datum).append(" ");
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

        return "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + Result_Numbers;
    }

    private void writeFile(String toFileName, String text) {
        File finalFile = new File(toFileName);

        try {
            BufferedWriter writerToFinalFile = new BufferedWriter(new FileWriter(finalFile, true));
            writerToFinalFile.write(text);
            writerToFinalFile.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
