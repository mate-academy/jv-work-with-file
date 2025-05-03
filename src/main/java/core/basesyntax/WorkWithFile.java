package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyStats = 0;
        int buyStats = 0;
        int result = 0;
        String[] generalizedData = readFile(fromFileName).split(System.lineSeparator());

        for (String data : generalizedData) {
            if (data.startsWith("supply")) {
                supplyStats += Integer.parseInt(data.split(",")[1]);
            } else if (data.startsWith("buy")) {
                buyStats += Integer.parseInt(data.split(",")[1]);
            }
        }

        result = supplyStats - buyStats;
        StringBuilder reportData = new StringBuilder();

        reportData.append("supply,").append(supplyStats).append(System.lineSeparator())
                .append("buy,").append(buyStats).append(System.lineSeparator())
                .append("result,").append(result);
        String report = reportData.toString();

        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
