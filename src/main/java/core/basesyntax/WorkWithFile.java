package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        int[] numbers = readFile(fromFileName);
        writeToFile(createReport(numbers[0], numbers[1], numbers[2]), toFileName);
    }

    private int[] readFile(String fromFileName) {
        int supplySum = 0;
        int buySum = 0;
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(",");
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
        return stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file" + toFileName, e);
        }
    }

}
