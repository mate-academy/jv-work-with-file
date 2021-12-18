package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int totalSupply = 0;
    private int totalBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        calculateSupplyAndBuy(readFromFile(fromFileName));
        String report = createReport(totalSupply, totalBuy);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine).append(" ");
                readLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private void calculateSupplyAndBuy(String data) {
        String[] rowList = data.split(" ");
        for (String row : rowList) {
            String operationType = row.substring(0, row.indexOf(","));
            String amount = row.substring(row.indexOf(",") + 1);
            if (operationType.equals("supply")) {
                totalSupply += Integer.parseInt(amount);
            } else if (operationType.equals("buy")) {
                totalBuy += Integer.parseInt(amount);
            }
        }
    }

    private String createReport(int supply, int buy) {
        StringBuilder report = new StringBuilder();
        return (report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy)).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
