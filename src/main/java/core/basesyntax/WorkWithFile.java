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
    public static final String SUPPLY = "supply,";
    public static final String BUY = "buy,";
    public static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readData(fromFileName);
        int[] profit = amountOfProfit(data);
        String[] report = report(profit);
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
            if (stringData.split(",")[0].equals("supply")) {
                countProfit[0] += Integer.parseInt(stringData.split(",")[1]);
            }
            if (stringData.split(",")[0].equals("buy")) {
                countProfit[1] += Integer.parseInt(stringData.split(",")[1]);
            }
        }
        countProfit[2] = countProfit[0] - countProfit[1];
        return countProfit;
    }

    private String[] report(int[] profit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY)
                .append(profit[0])
                .append("\n")
                .append(BUY)
                .append(profit[1])
                .append("\n")
                .append(RESULT)
                .append(profit[2]);

        return stringBuilder.toString().split("\n");
    }

    private void writeResult(String[] report, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String string : report) {
                bufferedWriter.write(string);
                bufferedWriter.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cant write data.");
        }
    }
}
