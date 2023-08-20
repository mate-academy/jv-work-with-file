package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

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
            String[] operationInfo = line.split(SEPARATOR);
            final String value = operationInfo[1];
            if (operationInfo[0].equals(SUPPLY)) {
                supplySum += Integer.parseInt(value);
            } else {
                buySum += Integer.parseInt(value);
            }
        }
        int result = supplySum - buySum;
        return new StringBuilder(SUPPLY).append(SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum).append(System.lineSeparator())
                .append("result").append(SEPARATOR).append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can not write data to file: " + toFileName, e);
        }
    }
}
