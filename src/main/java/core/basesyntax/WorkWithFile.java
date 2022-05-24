package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputData = getDataFromFile(fromFileName);
        String report = createReport(inputData);
        writeDataToFile(report, toFileName);
    }

    private String[] getDataFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't found the file: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return builder.toString().split(" ");
    }

    private String createReport(String[] data) {
        StringBuilder builder = new StringBuilder();
        int sumSupply = getSumFromSupply(data);
        int sumBuy = getSumFromBuy(data);
        return builder.append(SUPPLY).append(",").append(sumSupply).append(System.lineSeparator())
                .append(BUY).append(",").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(sumSupply - sumBuy).toString();
    }

    private int getSumFromSupply(String[] data) {
        int sumOfSupply = 0;
        for (int i = 0; i < data.length; i++) {
            String[] wordAndNum = data[i].split(",");
            if (wordAndNum[0].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(wordAndNum[1]);
            }
        }
        return sumOfSupply;
    }

    private int getSumFromBuy(String[] data) {
        int sumOfBuy = 0;
        for (int i = 0; i < data.length; i++) {
            String[] wordAndNum = data[i].split(",");
            if (wordAndNum[0].equals(BUY)) {
                sumOfBuy += Integer.parseInt(wordAndNum[1]);
            }
        }
        return sumOfBuy;
    }

    private void writeDataToFile(String report, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
