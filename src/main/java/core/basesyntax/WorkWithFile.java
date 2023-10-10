package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        int sum = 0;
        int buy = 0;
        for (int i = 0; i < words.length; i += 2) {
            final String currentWord = words[i];
            final int quantity = Integer.parseInt(words[i + 1]);
            if (currentWord.equals(SUPPLY)) {
                sum += quantity;
            } else {
                buy += quantity;
            }
        }
        writeReport(toFileName, generateReport(sum, buy));
    }

    private void writeReport(String toFileName, String report) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from a file: " + inputFile.getName(), e);
        }
        return stringBuilder.toString();
    }
}
