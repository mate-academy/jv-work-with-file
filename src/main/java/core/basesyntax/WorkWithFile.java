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
        String[] dataFromFile = getDataFromFile(fromFileName);
        int sumSupply = getSumOfSupply(dataFromFile);
        int sumBuy = getSumOfBuy(dataFromFile);
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY + "," + sumSupply).append(System.lineSeparator())
                .append(BUY + "," + sumBuy).append(System.lineSeparator())
                .append("result,").append(sumSupply - sumBuy);
        writeDataToFile(report.toString(), toFileName);
    }

    private String[] getDataFromFile(String path) {
        File file = new File(path);
        StringBuilder data = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                data.append((char) value);
                value = reader.read();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't found file", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return data.toString().split("\\W+");
    }

    private void writeDataToFile(String report, String path) {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private boolean isInteger(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int getSumOfSupply(String[] data) {
        int sumOfSupply = 0;
        for (int i = 0; i < data.length; i++) {
            if (!isInteger(data[i]) && data[i].equals(SUPPLY)) {
                sumOfSupply += Integer.parseInt(data[i + 1]);
            }
        }
        return sumOfSupply;
    }

    private int getSumOfBuy(String[] data) {
        int sumOfBuy = 0;
        for (int i = 0; i < data.length; i++) {
            if (!isInteger(data[i]) && data[i].equals(BUY)) {
                sumOfBuy += Integer.parseInt(data[i + 1]);
            }
        }
        return sumOfBuy;
    }
}
