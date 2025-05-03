package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] resultFile = readFromFile(fromFileName);
        String report = createReport(resultFile);
        writeToFile(toFileName, report);
    }

    public String[] readFromFile(String fromFileName) {
        String[] dateFromLine;
        String data;
        int result;
        int supply = 0;
        int buy = 0;
        String sypply = "supply";
        String comma = ",";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((data = bufferedReader.readLine()) != null) {
                if (data.contains(sypply)) {
                    dateFromLine = data.split(comma);
                    String amount = dateFromLine[1];
                    int numbers = Integer.parseInt(amount);
                    supply += numbers;
                }
                if (data.contains("buy")) {
                    String[] values = data.split(comma);
                    int money = Integer.parseInt(values[1]);
                    buy += money;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file ", e);
        }
        result = supply - buy;
        String supplyValye = "" + supply;
        String buyValue = "" + buy;
        String resultValue = "" + result;
        return new String[] {supplyValye, buyValue, resultValue};
    }

    public String createReport(String[] result) {
        String supply = result[0];
        String buy = result[1];
        String resultValue = result[2];
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(resultValue);
        return report.toString();
    }

    public void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        }
    }
}
