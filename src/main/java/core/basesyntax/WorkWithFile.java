package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataSplit = getDataFromFile(fromFileName).split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : dataSplit) {
            String name = line.substring(0, line.indexOf(','));
            int value = Integer.parseInt(line.substring(line.indexOf(',') + 1));
            if (SUPPLY.equals(name)) {
                supplyAmount += value;
            }
            if (BUY.equals(name)) {
                buyAmount += value;
            }
        }
        writeToFile(toFileName, buildReport(supplyAmount, buyAmount));
    }

    private String getDataFromFile(String fileName) {
        StringBuilder fileContant = new StringBuilder();
        try (BufferedReader fromFile = new BufferedReader(new FileReader(fileName))) {
            String line = fromFile.readLine();
            while (line != null) {
                fileContant.append(line).append(System.lineSeparator());
                line = fromFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read " + fileName, e);
        }
        return fileContant.toString();
    }

    private String buildReport(int supplyAmount, int buyAmount) {
        StringBuilder report = new StringBuilder();
        return String.valueOf(report.append("supply,")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result")
                .append(",").append(supplyAmount - buyAmount));
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(fileName))) {
            toFile.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to " + fileName, e);
        }
    }
}
