package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_CONSTANT = "supply";
    private static final String BUY_CONSTANT = "buy";
    private static final String RESULT_CONSTANT = "result";
    private static final String WORD_SPLIT_REGEX = "\\W+";
    private static final String SEPARATOR = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String generateReport(String data) {
        String[] words = data.split(WORD_SPLIT_REGEX);
        int supplySum = 0;
        int buySum = 0;

        for (int i = 0; i < words.length; i += 2) {
            if (words[i].equals(SUPPLY_CONSTANT)) {
                supplySum += Integer.parseInt(words[i + 1]);
            } else if (words[i].equals(BUY_CONSTANT)) {
                buySum += Integer.parseInt(words[i + 1]);
            }
        }

        int resultSum = supplySum - buySum;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_CONSTANT).append(",").append(supplySum)
                .append(System.lineSeparator());
        stringBuilder.append(BUY_CONSTANT).append(",").append(buySum)
                .append(System.lineSeparator());
        stringBuilder.append(RESULT_CONSTANT).append(",").append(resultSum);
        return stringBuilder.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File inputFile = new File(fromFileName);
            FileReader fileReader = new FileReader(inputFile);
            int value = fileReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = fileReader.read();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Failed to find a file: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from a file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File outputFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create a file: " + toFileName, e);
        }
    }
}
