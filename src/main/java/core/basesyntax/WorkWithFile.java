package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SPLIT = ",";
    private static final int INDEX_ZERO = 0;
    private static final String SUPPLY = "supply";
    private static final int SECOND_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        int[] numbers = readFile(fromFileName);
        StringBuilder text = creatText(numbers[0], numbers[1], numbers[2]);
        writeInFile(text.toString(), toFile);
    }

    private int[] readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int supplySum = 0;
            int buySum = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineArray = line.split(SPLIT);
                if (lineArray[INDEX_ZERO].equals(SUPPLY)) {
                    supplySum += Integer.parseInt(lineArray[SECOND_INDEX]);
                } else {
                    buySum += Integer.parseInt(lineArray[SECOND_INDEX]);
                }
            }
            int result = supplySum - buySum;
            return new int[]{buySum, supplySum, result};
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file " + fromFileName, e);
        }
    }

    private StringBuilder creatText(int buySum, int supplySum, int result) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                .append("buy,").append(buySum).append(System.lineSeparator())
                .append("result,").append(result);
    }

    private void writeInFile(String text, File toFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFile,e);
        }
    }
}
