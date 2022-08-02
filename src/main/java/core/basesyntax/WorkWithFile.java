package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int TYPE_OPERATION = 0;
    public static final int SUM_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readFromFile(fromFileName);
        String statisticOfData = statistic(linesFromFile);
        writeDataToFile(toFileName, statisticOfData);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lines;
            while ((lines = reader.readLine()) != null) {
                builder.append(lines).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fromFileName, e);
        }
        return builder.toString().split(" ");
    }

    private String statistic(String[] linesFromFile) {
        int amountBuy = 0;
        int amountSupply = 0;
        for (String lineFromFile : linesFromFile) {
            String[] splitLine = lineFromFile.split(",");
            String typeOfOperation = splitLine[TYPE_OPERATION];
            int sum = Integer.parseInt(splitLine[SUM_INDEX]);
            if (typeOfOperation.equals("buy")) {
                amountBuy += sum;
            } else if (typeOfOperation.equals("supply")) {
                amountSupply += sum;
            }
        }
        return reportOfData(amountBuy, amountSupply);
    }

    private String reportOfData(int amountBuy, int amountSupply) {
        return "supply," + amountSupply + System.lineSeparator()
                + "buy," + amountBuy + System.lineSeparator()
                + "result," + (amountSupply - amountBuy);

    }

    private void writeDataToFile(String toFileName, String statisticOfData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statisticOfData);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file " + toFileName, e);
        }
    }
}
