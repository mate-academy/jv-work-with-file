package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = readFromFile(fromFileName);
        StringBuilder report = filteredData(inputData);
        writeToFile(toFileName, report.toString());
    }

    private String readFromFile(String fromFileName) {
        File input = new File(fromFileName);
        StringBuilder inputData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String value = reader.readLine();
            while (value != null) {
                inputData.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputData.toString();
    }

    private void writeToFile(String toFileName, String data) {
        File output = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder filteredData(String inputData) {
        String[] data = inputData.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : data) {
            String[] lineItems = line.split(",");
            buyAmount += (lineItems[0].equals("buy")) ? Integer.valueOf(lineItems[1]) : 0;
            supplyAmount += (lineItems[0].equals("supply")) ? Integer.valueOf(lineItems[1]) : 0;
        }
        StringBuilder report = createReport(buyAmount,supplyAmount);
        return report;
    }

    private StringBuilder createReport(int buy, int supply) {
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
    }
}
