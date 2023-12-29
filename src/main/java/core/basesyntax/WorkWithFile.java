package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static int totalSupply;
    private static int totalBuy;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileData = readFile(fromFileName);
        String countData = countStatistic(fileData);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(countData);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
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
        totalSupply = 0;
        totalBuy = 0;
        String[] splitLine = data.split(System.lineSeparator());
        for (String line : splitLine) {
            String[] splitData = line.split(",");
            if (splitData[0].equals("supply")) {
                totalSupply += Integer.parseInt(splitData[1]);
            }
            if (splitData[0].equals("buy")) {
                totalBuy += Integer.parseInt(splitData[1]);
            }
        }
        StringBuilder builderResult = new StringBuilder();
        builderResult.append("supply,").append(totalSupply).append(System.lineSeparator());
        builderResult.append("buy,").append(totalBuy).append(System.lineSeparator());
        builderResult.append("result,").append(totalSupply - totalBuy);
        return builderResult.toString();
    }
}
