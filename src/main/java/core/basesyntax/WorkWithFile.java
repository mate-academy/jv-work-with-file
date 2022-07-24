package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = supply(data);
        writeToFile(toFileName, report);
    }

    public String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
        return builder.toString();
    }

    public String supply(String text) {
        int summSupplies = 0;
        int summPurchases = 0;
        String[] lines = text.split(System.lineSeparator());
        for (String words : lines) {
            String[] resultSplited = words.split(",");
            if (resultSplited[0].startsWith("s")) {
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