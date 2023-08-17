package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final char separator = ',';
    private String supply = "supply";
    private String buy = "buy";
    private String result = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = generateReport(readFromFile(fromFileName));
        writeToFile(toFileName, result);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String generateReport(String fileData) {
        int buySum = 0;
        int supplySum = 0;
        String[] dataLines = fileData.split(System.lineSeparator());
        for (String line : dataLines) {
            String[] operationInfo = line.split(String.valueOf(separator));
            final String value = operationInfo[1];
            if (operationInfo[0].equals(supply)) {
                supplySum += Integer.parseInt(value);
            } else {
                buySum += Integer.parseInt(value);
            }
        }
        int result = supplySum - buySum;
        return new StringBuilder(supply).append(separator).append(supplySum)
                .append(System.lineSeparator())
                .append(buy).append(separator).append(buySum).append(System.lineSeparator())
                .append("result").append(separator).append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file: " + toFileName, e);
        }
    }
}
