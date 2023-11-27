package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class WorkWithFile {
    private static final String SPLITERATOR = ",";
    private static final String CASE_BUY = "buy";
    private static final String CASE_SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String EXCEPTION_MESSAGE = "Key doesn't found";
    private static final String PARSE_EXCEPTION_MESSAGE = "Something went wrong";
    private static final int SUPPLY_VALUE = 1;
    private static final int BUY_VALUE = 3;
    private static final int KEY = 0;
    private static final int KEY_VALUE = 1;
    private static final String SPLIT_BY_NEW_LINE = System.lineSeparator();
    private static final int END_FILE_CODE = -1;

    public void getStatistic(String fromFileName, String toFileName) {
        int sumForBuy = 0;
        int sumForSupply = 0;
        StringBuilder textForSavingToFile = new StringBuilder();
        StringBuilder constructTextFromFile = new StringBuilder();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                FileWriter writer = new FileWriter(toFileName)
        ) {
            readFile(reader, constructTextFromFile);
            String[] splitConstructTextFromFile =
                    getSplitConstructTextFromFile(constructTextFromFile);

            String[] processData = processData(splitConstructTextFromFile, sumForSupply, sumForBuy);

            StringBuilder textForSavingFile =
                    generateReport(
                            Integer.parseInt(processData[BUY_VALUE]),
                            Integer.parseInt(processData[SUPPLY_VALUE]),textForSavingToFile);
            writeToFile(textForSavingFile.toString(), writer);

        } catch (IOException e) {
            throw new NoSuchElementException(PARSE_EXCEPTION_MESSAGE);
        }
    }

    private void writeToFile(String textForSavingFile, FileWriter writer) {
        try {
            writer.write(textForSavingFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] processData(
            String[] splitConstructTextFromFile,
            int sumForSupply,
            int sumForBuy) {
        int copySumForSupply = sumForSupply;
        int copySumForBuy = sumForBuy;
        for (String s : splitConstructTextFromFile) {
            String[] currentLine = s.split(SPLITERATOR);
            switch (currentLine[KEY]) {
                case CASE_BUY -> copySumForBuy += Integer.parseInt(currentLine[KEY_VALUE]);
                case CASE_SUPPLY -> copySumForSupply += Integer.parseInt(currentLine[KEY_VALUE]);
                default -> throw new NoSuchElementException(EXCEPTION_MESSAGE);
            }
        }
        return new String[]{
                CASE_SUPPLY,
                String.valueOf(copySumForSupply),
                CASE_BUY,
                String.valueOf(copySumForBuy)
        };
    }

    private StringBuilder generateReport(int sumForBuy, int sumForSupply,
            StringBuilder textForSavingToFile) {
        return textForSavingToFile.append(CASE_SUPPLY).append(SPLITERATOR)
                .append(sumForSupply)
                .append(System.lineSeparator())
                .append(CASE_BUY)
                .append(SPLITERATOR)
                .append(sumForBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SPLITERATOR)
                .append(sumForSupply - sumForBuy);
    }

    private String[] getSplitConstructTextFromFile(StringBuilder constructTextFromFile) {
        return constructTextFromFile.toString().split(SPLIT_BY_NEW_LINE);
    }

    private void readFile(
            BufferedReader reader,
            StringBuilder constructTextFromFile) {
        int value;
        try {
            value = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
        while (value != END_FILE_CODE) {
            constructTextFromFile.append((char) value);
            try {
                value = reader.read();
            } catch (IOException e) {
                throw new RuntimeException(EXCEPTION_MESSAGE);
            }
        }
    }
}
