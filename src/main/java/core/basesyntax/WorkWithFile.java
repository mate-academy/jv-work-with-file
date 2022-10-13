package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String strFromFile = readFromFile(fromFileName);
        String[] fromFile = strFromFile.split(System.lineSeparator());
        writeToFile(toFileName, createReport(fromFile));
    }

    public String readFromFile(String fromFileName) {
        File nameFileFrom = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nameFileFrom));
            String line = reader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fromFileName, e);
        }
        return builder.toString();
    }

    public void writeToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    public String createReport(String[] fromFile) {
        StringBuilder writeToFile = new StringBuilder();
        int resultForBuy = 0;
        int resultForSupply = 0;
        int profit = 0;
        for (int i = 0; i < fromFile.length; i++) {
            String[] record = fromFile[i].split(",");
            if (record[OPERATION_INDEX].equals("buy")) {
                resultForBuy = resultForBuy + Integer.parseInt(record[AMOUNT_INDEX]);
            } else if (record[OPERATION_INDEX].equals("supply")) {
                resultForSupply = resultForSupply + Integer.parseInt(record[AMOUNT_INDEX]);
            }
        }
        profit = resultForSupply - resultForBuy;
        return writeToFile.append("supply,").append(resultForSupply)
                .append(System.lineSeparator())
                .append("buy,").append(resultForBuy)
                .append(System.lineSeparator())
                .append("result,").append(profit).toString();
    }
}
