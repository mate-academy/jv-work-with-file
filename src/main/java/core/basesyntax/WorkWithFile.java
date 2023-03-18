package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_REGEXP = "\\W+";
    private static final String WORD_TO_DETECT_SUPPLY_COUNTER_NUMBER = "supply";
    private static final String WORD_TO_DETECT_BUY_COUNTER_NUMBER = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder textFromFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                textFromFile.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }

        String[] textFromFileAsArray = textFromFile.toString().split(SPLIT_REGEXP);
        int supplyCounter = 0;
        int buyCounter = 0;
        for (int i = 0; i < textFromFileAsArray.length; i++) {
            if (textFromFileAsArray[i].equals(WORD_TO_DETECT_SUPPLY_COUNTER_NUMBER)) {
                supplyCounter += Integer.parseInt(textFromFileAsArray[i + 1]);
            }
            if (textFromFileAsArray[i].equals(WORD_TO_DETECT_BUY_COUNTER_NUMBER)) {
                buyCounter += Integer.parseInt(textFromFileAsArray[i + 1]);
            }
        }

        StringBuilder report = new StringBuilder().append("supply,")
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyCounter)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyCounter - buyCounter);

        File file1 = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
