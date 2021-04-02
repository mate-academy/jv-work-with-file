package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readData(fromFileName);
        int[] profit = amountOfProfit(data);
        String report = getReport(profit);
        writeResult(report, toFileName);
    }

    private String[] readData(String fromFileName) {
        File file = new File(fromFileName);
        ArrayList<String> data = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String sentence = bufferedReader.readLine();
            while (sentence != null) {
                data.add(sentence);
                sentence = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.");
        } catch (IOException e) {
            throw new RuntimeException("Cant read data from the file.");
        }
        return data.toArray(new String[data.size()]);
    }

    private int[] amountOfProfit(String[] data) {
        int[] countProfit = new int[3];

        for (String stringData : data) {
            String supplyOrBuy = stringData.split(COMMA)[0];

            if (supplyOrBuy.equals(SUPPLY)) {
                countProfit[0] += Integer.parseInt(stringData.split(",")[1]);
            }
            if (supplyOrBuy.equals(BUY)) {
                countProfit[1] += Integer.parseInt(stringData.split(",")[1]);
            }
        }
        countProfit[2] = countProfit[0] - countProfit[1];
        return countProfit;
    }

    private String getReport(int[] profit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(COMMA)
                .append(profit[0])
                .append(System.lineSeparator())
                .append(BUY)
                .append(COMMA)
                .append(profit[1])
                .append(System.lineSeparator())
                .append(RESULT)
                .append(COMMA)
                .append(profit[2]);

        return stringBuilder.toString();
    }

    private void writeResult(String report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, false))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data.");
        }
    }
}
