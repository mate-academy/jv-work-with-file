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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(fileData);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file", e);
        }
    }

    private String readFile(String fileName) {
        totalSupply = 0;
        totalBuy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(",");
                if (splitLine[0].equals("supply")) {
                    totalSupply += Integer.parseInt(splitLine[1]);
                }
                if (splitLine[0].equals("buy")) {
                    totalBuy += Integer.parseInt(splitLine[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t find file " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fileName, e);
        }
        StringBuilder builderResult = new StringBuilder();
        builderResult.append("supply,").append(totalSupply).append(System.lineSeparator());
        builderResult.append("buy,").append(totalBuy).append(System.lineSeparator());
        builderResult.append("result,").append(totalSupply - totalBuy);
        return builderResult.toString();
    }
}
