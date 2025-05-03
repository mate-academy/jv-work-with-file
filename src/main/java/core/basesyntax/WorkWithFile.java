package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX = ",";
    private static final String SUPPLY = "supply";
    private static final int NAME_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        writeFile(readFile(fromFileName), toFile);
    }

    private int[] readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int buySum = 0;
            int supplySum = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(REGEX);
                if (lineArray[NAME_INDEX].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(lineArray[NUMBER_INDEX]);
                } else {
                    buySum += Integer.parseInt(lineArray[NUMBER_INDEX]);
                }
            }
            int result = supplySum - buySum;
            return new int[]{buySum, supplySum, result};
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
    }

    private void writeFile(int[] arrayNumbers, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(createResultText(arrayNumbers[0], arrayNumbers[1],
                    arrayNumbers[2]).toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFile, e);
        }
    }

    private StringBuilder createResultText(int buySum, int supplySum, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);
    }
}
