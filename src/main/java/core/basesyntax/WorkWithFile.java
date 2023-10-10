package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String supply = "supply";
    private static final String buy = "buy";
    private static final String result = "result";
    private static final String wordSplitPattern = "\\W+";
    private static final String comma = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        final File mainFile = new File(fromFileName);
        final String[] words = readFromFile(mainFile).split(wordSplitPattern);
        int sum = 0;
        int buy = 0;
        for (int i = 0; i < words.length; i += 2) {
            String currentWord = words[i];
            int quantity = Integer.parseInt(words[i + 1]);

            if (currentWord.equals(supply)) {
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
                .append(supply).append(comma).append(supplySum).append(System.lineSeparator())
                .append(buy).append(comma).append(buySum).append(System.lineSeparator())
                .append(result).append(comma).append(resultSum).toString();
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
