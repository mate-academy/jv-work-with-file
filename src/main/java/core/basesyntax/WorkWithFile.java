package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int sumSupply = 0;
        int sumBuy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                String operation = lineSplit[0];
                int digit = Integer.parseInt(lineSplit[1]);
                if (operation.equals("supply")) {
                    sumSupply += digit;
                } else if (operation.equals("buy")) {
                    sumBuy += digit;
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("I can't perform the operation!", exception);
        }
        String report = createReport(sumSupply, sumBuy);
        writeToFile(toFileName, report);
    }

    public String createReport(int sumSupply, int sumBuy) {
        int result = sumSupply - sumBuy;
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(sumSupply).append(System.lineSeparator());
        report.append("buy,").append(sumBuy).append(System.lineSeparator());
        report.append("result,").append(result).append(System.lineSeparator());
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException exception) {
            throw new RuntimeException("I can`t do this operation!", exception);
        }
    }
}
