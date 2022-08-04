package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_TOTAL_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int BUY_TOTAL_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        int [] statistic = countStatistic(data);
        String content = prepareReport(statistic);
        writeToFile(content,toFileName);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not read this file: " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private int [] countStatistic(String data) {
        int [] statistic = new int [2];
        String [] lines = data.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            if (lines [i].startsWith("supply")) {
                statistic [SUPPLY_TOTAL_INDEX] += Integer.parseInt(lines [i]
                        .split(",")[AMOUNT_INDEX]);
            }
            if (lines [i].startsWith("buy")) {
                statistic [BUY_TOTAL_INDEX] += Integer.parseInt(lines [i].split(",")[AMOUNT_INDEX]);
            }
        }
        return statistic;
    }

    private String prepareReport(int[] statistic) {
        int totalSupply = statistic[0];
        int totalBuy = statistic[1];
        return new StringBuilder()
        .append("supply,").append(String.valueOf(totalSupply)).append(System.lineSeparator())
        .append("buy,").append(String.valueOf(totalBuy)).append(System.lineSeparator())
        .append("result,").append(String.valueOf(totalSupply - totalBuy)).toString();
    }

    private void writeToFile(String content, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file: " + fileName, e);
        }
    }
}


