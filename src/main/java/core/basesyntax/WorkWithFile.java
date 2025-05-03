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
        String textFromFile = readFromFile(fromFileName);
        String report = reportCreation(textFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file: " + fromFileName, e);
        }
        return stringBuilder.toString();
    }

    private String reportCreation(String text) {
        String[] textFromFileAsArray = text.split(SPLIT_REGEXP);
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
        StringBuilder stringBuilderReport = new StringBuilder().append("supply,")
                .append(supplyCounter)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyCounter)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyCounter - buyCounter);
        return stringBuilderReport.toString();
    }

    private void writeToFile(String report, String writeToFile) {

        File file1 = new File(writeToFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + writeToFile, e);
        }
    }
}
