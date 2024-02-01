package core.basesyntax;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> newReport = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                newReport.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t to open this file", e);
        }

        List<String> result = statisticReport(newReport);
        totalReport(result, toFileName);
    }

    private List<String> statisticReport(List<String> internalReport) {
        int totalPurchase = 0;
        int totalSupply = 0;

        for (String line : internalReport) {
            String[] list = line.split(",");
            if (list.length == 2) {
                String word = list[0].trim();
                int amount = Integer.parseInt(list[1].trim());

                if ("купити".equals(word)) {
                    totalPurchase += amount;
                } else if ("поставка".equals(word)) {
                    totalSupply += amount;
                }
            }
        }

        List<String> outReport = new ArrayList<>();
        outReport.add("постачання," + totalSupply);
        outReport.add("купівля," + totalPurchase);
        outReport.add("результат," + (totalSupply - totalPurchase));

        return outReport;
    }

    public void totalReport(List<String> report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String lines : report) {
                bufferedWriter.write(lines);
                bufferedWriter.newLine();
                System.out.println(lines);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t to write", e);
        }
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "grape.csv");

    }
}
