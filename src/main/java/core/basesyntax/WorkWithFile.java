package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String[] buyOrSupply = {"supply", "buy"};
        final int[] sums = new int [buyOrSupply.length];
        StringBuilder report = new StringBuilder();
        getSum(sums, buyOrSupply, fromFileName);
        formulateReport(report, sums, buyOrSupply);
        saveToFile(report,toFileName);

    }

    private void getSum(int[] sums, String[] buyOrSupply, String fromFileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] fileLine = value.split(",");
                for (int j = 0; j < buyOrSupply.length; j++) {
                    if (fileLine[0].equals(buyOrSupply[j])) {
                        int currentBuyOrSupplyValue = Integer.parseInt(fileLine[1]);
                        sums[j] += currentBuyOrSupplyValue;
                        value = reader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void formulateReport(StringBuilder report, int[] sums, String[] buyOrSupply) {
        final int result = sums[0] - sums[1];
        String stringResult = "result";

        report.append(buyOrSupply[0]).append(",").append(sums[0])
                .append(System.lineSeparator()).append(buyOrSupply[1]).append(",")
                .append(sums[1]).append(System.lineSeparator()).append(stringResult).append(",")
                .append(result);
    }

    private void saveToFile(StringBuilder report, String toFileName) {

        String finallyReport = report.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(finallyReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
