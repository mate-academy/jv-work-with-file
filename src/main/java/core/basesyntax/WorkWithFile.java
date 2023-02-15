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
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] numbers = readFile(fromFileName);
        writeToFile(createReport(numbers[0], numbers[1], numbers[2]), toFileName);
    }

    private int[] readFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(COMMA);
                if (lineArray[0].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(lineArray[1]);
                } else {
                    buySum += Integer.parseInt(lineArray[1]);
                }
            }
            int result = supplySum - buySum;
            return new int[]{supplySum, buySum, result};
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file " + fromFileName, e);
        }
    }

    private String createReport(int supplySum, int buySum, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(SUPPLY).append(COMMA).append(supplySum)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + toFileName, e);
        }
    }
}
