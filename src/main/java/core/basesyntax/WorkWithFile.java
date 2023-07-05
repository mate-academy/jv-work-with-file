package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        String report = workWithFile.createReportFromCsvFile(fromFileName);
        workWithFile.writeToFile(toFileName, report);
    }

    private String createReportFromCsvFile(String fileName) {
        int totalBuy = 0;
        int totalSupply = 0;
        final int buy = 0;
        final int supply = 0;
        final int amount = 1;
        final String separator = ",";
        final String supplyString = "supply";
        final String buyString = "buy";
        String line;
        StringBuilder info = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split(separator);
                if (word[buy].equals(buyString)) {
                    totalBuy += Integer.parseInt(word[amount]);
                }
                if (word[supply].equals(supplyString)) {
                    totalSupply += Integer.parseInt(word[amount]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fileName, e);
        }
        info.append(supplyString).append(separator).append(totalSupply)
                .append(System.lineSeparator());
        info.append(buyString).append(separator).append(totalBuy).append(System.lineSeparator());
        info.append("result,").append(totalSupply - totalBuy);
        return info.toString();
    }

    private void writeToFile(String toFile, String info) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFile, e);
        }
    }
}
