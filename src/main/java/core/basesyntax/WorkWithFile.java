package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int OPERATION = 0;
    public static final int INDEX_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readFromFile(fromFileName);
        String statisticData = statistic(linesFromFile);
        writeDataToFile(toFileName, statisticData);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lines;
            while ((lines = reader.readLine()) != null) {
                builder.append(lines).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read File " + fromFileName, e);
        }
        return builder.toString().split(" ");
    }

    private String statistic(String[] linesFromFile) {
        int sumBuy = 0;
        int sumSupply = 0;
        for (String lines : linesFromFile) {
            String[] splitLine = lines.split(",");
            String operationType = splitLine[OPERATION];
            int sum = Integer.parseInt(splitLine[INDEX_SUM]);
            if (operationType.equals("supply")) {
                sumSupply += sum;
            } else if (operationType.equals("buy")) {
                sumBuy += sum;
            }
        }
        return reportData(sumSupply, sumBuy);
    }

    private String reportData(int sumSupply, int sumBuy) {
        return "supply," + sumSupply + System.lineSeparator()
                + "buy," + sumBuy + System.lineSeparator()
                + "result," + (sumSupply - sumBuy);
    }

    private void writeDataToFile(String toFileName, String statisticData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statisticData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
