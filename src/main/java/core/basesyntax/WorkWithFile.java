package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName);
        String statistic = getStatistic(lines);
        writeToFile(toFileName, statistic);
    }

    private String getStatistic(String[] lines) {
        int sumBuy = 0;
        int sumSupply = 0;
        for (String line : lines) {
            String splitOperation = line.split(",")[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(line.split(",")[AMMOUNT_INDEX]);
            if (splitOperation.equals("buy")) {
                sumBuy += amount;
            } else if (splitOperation.equals("supply")) {
                sumSupply += amount;
            }
        }
        return createReport(sumBuy, sumSupply);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName + e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private String createReport(int sumBuy, int sumSupply) {
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(sumSupply - sumBuy);
        return builder.toString();
    }

    private void writeToFile(String toFileName, String statistic) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName + e);
        }
    }
}
