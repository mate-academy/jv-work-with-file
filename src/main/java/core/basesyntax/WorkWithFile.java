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
    private static final int SPECIFIC_FIRST_ELEMENT = 0;
    private static final int SPECIFIC_SECOND_ELEMENT = 1;
    private static final String SPLIT_BY_NEW_LINE = "\n";
    private static final int END_FILE_CODE = -1;
    private static final int SET_LENGTH_TO_ZERO = 0;
    private final StringBuilder textForSavingToFile = new StringBuilder();
    private final StringBuilder constructTextFromFile = new StringBuilder();

    public void getStatistic(String fromFileName, String toFileName) {
        int sumForBuy = 0;
        int sumForSupply = 0;
        try (
                FileWriter writer = new FileWriter(toFileName);
                BufferedReader reader = new BufferedReader(new FileReader(fromFileName))
        ) {
            readFromFile(reader, constructTextFromFile);
            String[] splitConstructTextFromFile = getSplitConstructTextFromFile();

            for (String s : splitConstructTextFromFile) {
                String[] currentLine = s.split(SPLITERATOR);
                switch (currentLine[SPECIFIC_FIRST_ELEMENT]) {
                    case CASE_BUY ->
                            sumForBuy += Integer.parseInt(currentLine[SPECIFIC_SECOND_ELEMENT]);
                    case CASE_SUPPLY ->
                            sumForSupply += Integer.parseInt(currentLine[SPECIFIC_SECOND_ELEMENT]);
                    default -> throw new NoSuchElementException(EXCEPTION_MESSAGE);
                }
            }
            String textForSavingFile = constructResultTextForSavingFile(sumForBuy, sumForSupply);
            writer.write(textForSavingFile);
            constructTextFromFile.setLength(SET_LENGTH_TO_ZERO);
            textForSavingToFile.setLength(SET_LENGTH_TO_ZERO);
        } catch (IOException e) {
            throw new NoSuchElementException(e);
        }
    }

    private String constructResultTextForSavingFile(int sumForBuy, int sumForSupply) {
        return textForSavingToFile.append(CASE_SUPPLY).append(SPLITERATOR)
                .append(sumForSupply)
                .append(System.lineSeparator())
                .append(CASE_BUY)
                .append(SPLITERATOR)
                .append(sumForBuy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SPLITERATOR)
                .append(sumForSupply - sumForBuy)
                .toString();
    }

    private String[] getSplitConstructTextFromFile() {
        return constructTextFromFile.toString().split(SPLIT_BY_NEW_LINE);
    }

    private void readFromFile(
            BufferedReader reader,
            StringBuilder constructTextFromFile)
            throws IOException {
        int value = reader.read();
        while (value != END_FILE_CODE) {
            constructTextFromFile.append((char) value);
            value = reader.read();
        }
    }
}
