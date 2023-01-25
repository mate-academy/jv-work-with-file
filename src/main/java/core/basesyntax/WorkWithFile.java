package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        writeToFile(getReport(data), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + toFileName, e);
        }
    }

    private String getReport(String[] data) {
        int supplySum = 0;
        int buySum = 0;
        for (String record : data) {
            String[] values = record.split(SEPARATOR);
            if (values[TYPE].equals(SUPPLY)) {
                supplySum += Integer.parseInt(values[AMOUNT]);
            }
            if (values[TYPE].equals(BUY)) {
                buySum += Integer.parseInt(values[AMOUNT]);
            }
        }
        return new StringBuilder().append(SUPPLY).append(SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supplySum - buySum).toString();
    }
}
