package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName).split(System.lineSeparator());
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : lines) {
            String[] splittedLine = line.split(",");
            if (splittedLine[0].equals("supply")) {
                totalSupply += Integer.parseInt(splittedLine[1]);
            } else {
                totalBuy += Integer.parseInt(splittedLine[1]);
            }
        }
        writeToFile(createReport(totalSupply, totalBuy), toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find this file: " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from this file: " + fileName, e);
        }
        return builder.toString();
    }

    private String createReport(int totalSupply, int totalBuy) {
        StringBuilder builder = new StringBuilder();

        int totalResult = totalSupply - totalBuy;
        builder.append("supply,").append(totalSupply).append(System.lineSeparator())
                .append("buy,").append(totalBuy).append(System.lineSeparator())
                .append("result,").append(totalResult);

        return builder.toString();
    }

    private void writeToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't writ to this file: " + toFileName, e);
        }
    }
}
