package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private final String supplyWord = "supply";
    private final String buyWord = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = getFromFileSumOf(fromFileName, supplyWord);
        int buySum = getFromFileSumOf(fromFileName, buyWord);
        writeToFile(toFileName, supplySum, buySum);
    }

    private int getFromFileSumOf(String fromFileName, String word) {
        try (BufferedReader file = new BufferedReader(new FileReader(fromFileName))) {
            String line = file.readLine();
            int amountSum = 0;

            while (line != null) {
                amountSum += getAmountValue(line, word);
                line = file.readLine();
            }
            return amountSum;
        } catch (Exception e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private int getAmountValue(String line, String word) {
        return line.contains(word) ? Integer.parseInt(line.split(",")[1]) : 0;
    }

    private int getResultValue(int supplySum, int buySum) {
        return supplySum - buySum;
    }

    private void writeToFile(String toFileName, int supplySum, int buySum) {
        try (BufferedWriter toFile = new BufferedWriter(new FileWriter(toFileName))) {
            toFile.write(supplyWord + "," + supplySum + System.lineSeparator()
                    + buyWord + "," + buySum + System.lineSeparator()
                    + "result," + getResultValue(supplySum, buySum));
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
