package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File mainFile = new File(fromFileName);
        String[] words = readFromFile(mainFile).split("\\W+");

        int sumOSupply = 0;
        int buyOSupply = 0;

        for (int i = 0; i < words.length; i += 2) {
            if (words[i].equals(SUPPLY)) {
                sumOSupply += Integer.parseInt(words[i + 1]);
            } else if (words[i].equals(BUY)) {
                buyOSupply += Integer.parseInt(words[i + 1]);
            }
        }

        String report = generateReport(sumOSupply, buyOSupply);
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create a file: " + outputFile.getName(), e);
        }
    }

    private String generateReport(int supplySum, int buySum) {
        int resultSum = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator())
                .append(BUY).append(",").append(buySum).append(System.lineSeparator())
                .append(RESULT).append(",").append(resultSum);
        return stringBuilder.toString();
    }

    private String readFromFile(File inputFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(inputFile);
            int value = fileReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = fileReader.read();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed find a file: " + inputFile.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException("Failed read from a file: " + inputFile.getName(), e);
        }
        return stringBuilder.toString();
    }
}
