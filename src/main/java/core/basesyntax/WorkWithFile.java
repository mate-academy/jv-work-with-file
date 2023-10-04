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
    private static final String WORD_SPLIT_PATTERN = "\\W+";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        File mainFile = new File(fromFileName);
        String[] words = readFromFile(mainFile).split(WORD_SPLIT_PATTERN);

        int sumSupply = 0;
        int buySupply = 0;

        for (int i = 0; i < words.length; i += 2) {
            String currentWord = words[i];
            if (currentWord.equals(SUPPLY)) {
                sumSupply += Integer.parseInt(words[i + 1]);
            }
            if (currentWord.equals(BUY)) {
                buySupply += Integer.parseInt(words[i + 1]);
            }
        }

        String report = generateReport(sumSupply, buySupply);
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create a file: " + outputFile.getName(), e);
        }
    }

    private String generateReport(int supplySum, int buySum) {
        int resultSum = supplySum - buySum;

        return new StringBuilder()
                .append(SUPPLY).append(COMMA).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(resultSum).toString();

    }

    private String readFromFile(File inputFile) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(inputFile)) {
            int value;
            while ((value = fileReader.read()) != -1) {
                stringBuilder.append((char) value);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed find a file: " + inputFile.getName(), e);
        } catch (IOException e) {
            throw new RuntimeException("Failed read from a file: " + inputFile.getName(), e);
        }
        return stringBuilder.toString();
    }
}
