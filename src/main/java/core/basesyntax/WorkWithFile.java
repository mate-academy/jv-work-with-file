package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyCounter = 0;
        int buyCounter = 0;
        String[] lines = readDataFromFile(fromFileName).split(System.lineSeparator());
        for (String line : lines) {
            if (line.startsWith("supply")) {
                supplyCounter += Integer.parseInt(line.split(",")[AMOUNT_INDEX]);
            } else {
                buyCounter += Integer.parseInt(line.split(",")[AMOUNT_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("supply,").append(supplyCounter)
                .append(System.lineSeparator())
                .append("buy,").append(buyCounter)
                .append(System.lineSeparator())
                .append("result,").append(supplyCounter - buyCounter);
        String report = reportBuilder.toString();

        writeDataToFile(toFileName, report);
    }

    private String readDataFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from a file" + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to a file" + toFileName, e);
        }
    }
}
