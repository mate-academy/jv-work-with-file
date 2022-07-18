package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPECIAL_CHAR = "s";

    public void getStatistic(String fromFileName, String toFileName) {
        String readFile = readingFile(fromFileName);
        String supplier = supply(readFile);
        writeToFile(toFileName, supplier);
    }

    public String readingFile(String fromFile) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return builder.toString();
    }

    public String supply(String text) {
        int summSupplies = 0;
        int summPurchases = 0;
        String[] splited = text.split(System.lineSeparator());
        for (String line : splited) {
            String[] resultSplited = line.split(",");
            if (resultSplited[0].startsWith(SPECIAL_CHAR)) {
                summSupplies += Integer.parseInt(resultSplited[1]);
            } else {
                summPurchases += Integer.parseInt(resultSplited[1]);
            }
        }
        int result = summSupplies - summPurchases;

        return "supply," + summSupplies + System.lineSeparator()
                + "buy," + summPurchases + System.lineSeparator()
                + "result," + result;
    }

    public void writeToFile(String toFile, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
