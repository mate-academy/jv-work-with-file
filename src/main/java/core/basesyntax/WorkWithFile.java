package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFile(fromFileName);
        String countData = countStatistic(fileData);
        writeFile(countData, toFileName);
    }

    private void writeFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file " + fileName, e);
        }
    }

    private String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fileName, e);
        }
        return builder.toString().strip();
    }

    private String countStatistic(String data) {
        StringBuilder builder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;
        int number;
        String event;
        String[] splitLine = data.split(System.lineSeparator());
        for (String line : splitLine) {
            String[] splitData = line.split(",");
            event = splitData[ZERO];
            number = Integer.parseInt(splitData[ONE]);
            if (event.equals("supply")) {
                totalSupply += number;
            }
            if (event.equals("buy")) {
                totalBuy += number;
            }
        }
        builder.append("supply,").append(totalSupply).append(System.lineSeparator());
        builder.append("buy,").append(totalBuy).append(System.lineSeparator());
        builder.append("result,").append(totalSupply - totalBuy);
        return builder.toString();
    }
}
