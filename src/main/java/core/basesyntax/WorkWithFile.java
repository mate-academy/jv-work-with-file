package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesFromFile = readFromFile(fromFileName);
        String statistic = createStatistic(linesFromFile);
        writeDataToFile(toFileName, statistic);
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

    private String createStatistic(String[] linesFromFile) {
        int buyAmount = 0;
        int supplyAmount = 0;
        for (String lineFromFile : linesFromFile) {
            String[] splittedLine = lineFromFile.split(",");
            String typeOfOperation = splittedLine[OPERATION_TYPE_INDEX];
            int sum = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            if (typeOfOperation.equals("buy")) {
                buyAmount += sum;
            } else if (typeOfOperation.equals("supply")) {
                supplyAmount += sum;
            }
        }
        return createStatistic(buyAmount, supplyAmount);
    }

    private String createStatistic(int buyAmount, int supplyAmount) {
        StringBuilder statisticBuilder = new StringBuilder();
        statisticBuilder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount);
        return statisticBuilder.toString();
    }

    private void writeDataToFile(String toFileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file " + toFileName, e);
        }
    }
}
