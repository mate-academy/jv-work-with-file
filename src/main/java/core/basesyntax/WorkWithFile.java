package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String data = readDataFromFile(fromFileName);
        writeDataToFile(toFileName, data);
    }

    private String readDataFromFile(String fromFileName) {
        int countSupply = 0;
        int countBuy = 0;
        int countResult = 0;
        StringBuilder report = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] splitData = line.split(",");
                switch (splitData[0]) {
                    case "supply" :
                        countSupply += Integer.parseInt(splitData[1]);
                        break;
                    default:
                        countBuy += Integer.parseInt(splitData[1]);
                }
                line = bufferedReader.readLine();
            }
            countResult = countSupply - countBuy;

        } catch (IOException e) {
            throw new RuntimeException("Can't read data from a file" + fromFileName, e);
        }
        return report.append("supply,").append(countSupply)
                .append(System.lineSeparator())
                .append("buy,").append(countBuy)
                .append(System.lineSeparator())
                .append("result,").append(countResult).toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file" + toFileName, e);
        }
    }
}