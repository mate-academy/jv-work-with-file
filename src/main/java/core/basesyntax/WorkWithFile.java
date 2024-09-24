package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] data = readData(fromFileName);

        String resultData = calculateStatistics(data);

        writeData(toFileName, resultData);
    }

    public String[][] readData(String fileName) {
        //Read from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
            }

            String[] lines = stringBuilder.toString().split(LINE_SEPARATOR);
            String[][] data = new String[lines.length][2];
            for (int i = 0; i < lines.length; i++) {
                data[i] = lines[i].split(",");
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    public String calculateStatistics(String[][] data) {
        int totalSupply = 0;
        int totalBuy = 0;
        int result = 0;

        for (String[] entry : data) {
            String operation = entry[0];
            int amount = Integer.parseInt(entry[1]);

            if (operation.equals("supply")) {
                totalSupply += amount;
            } else if (operation.equals("buy")) {
                totalBuy += amount;
            }
        }

        result = totalSupply - totalBuy;

        StringBuilder resultData = new StringBuilder();
        resultData.append("supply,").append(totalSupply).append(LINE_SEPARATOR)
                .append("buy,").append(totalBuy).append(LINE_SEPARATOR)
                .append("result,").append(result);

        return resultData.toString();
    }

    public void writeData(String fileName, String data) {
        //Write to File
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file" + fileName, e);
        }
    }
}
