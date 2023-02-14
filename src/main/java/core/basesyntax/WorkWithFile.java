package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        for (String line : readFile(fromFileName).split(System.lineSeparator())) {
            String[] row = line.split(REGEX);
            if (row[NAME_INDEX].equals("buy")) {
                buySum += Integer.parseInt(row[NUMBER_INDEX]);
            } else {
                supplySum += Integer.parseInt(row[NUMBER_INDEX]);
            }
        }
        String report = createReport(supplySum, buySum);
        printFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
        return builder.toString();
    }

    private String createReport(int supplySum, int buySum) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(REGEX).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(REGEX).append(buySum).append(System.lineSeparator())
                .append("result").append(REGEX).append(supplySum - buySum);
        return stringBuilder.toString();
    }

    private void printFile(String toFileName, String info) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(info);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }
}
