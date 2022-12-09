package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = getReport(readFromFile(fromFileName));
        writeToFile(report, toFileName);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: ", e);
        }
    }

    private String getReport(String data) {
        int supplySum = 0;
        int buySum = 0;
        String[] lines = data.split(" ");
        for (String line : lines) {
            String[] splittedLine = line.split(String.valueOf(","));
            if (splittedLine[OPERATION_INDEX].equals(BUY)) {
                buySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else {
                supplySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder
                .append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator())
                .append(BUY).append(",").append(buySum).append(System.lineSeparator())
                .append(RESULT).append(",").append(supplySum - buySum).toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder readDataBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                readDataBuilder.append(line).append(" ");
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file: ", e);
        }
        return readDataBuilder.toString();
    }
}
