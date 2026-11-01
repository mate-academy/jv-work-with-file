package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final int TYPE = 0;
    private static final int VALUE = 1;
    private static final String EMPTY_SPACE = " ";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {

        File from = new File(fromFileName);
        File to = new File(toFileName);

        String namesBeforeSplit = "";
        String valueBeforeSplit = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(from))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedLine = line.split(COMMA);

                if (splittedLine.length != 2) {
                    throw new RuntimeException("Input file is incorrect");
                }

                namesBeforeSplit += splittedLine[TYPE] + EMPTY_SPACE;
                valueBeforeSplit += splittedLine[VALUE] + EMPTY_SPACE;
            }

            if (namesBeforeSplit.isEmpty() && valueBeforeSplit.isEmpty()) {
                throw new RuntimeException("Input file is empty");
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        String[] names = namesBeforeSplit.split(EMPTY_SPACE);
        String[] valuesString = valueBeforeSplit.split(EMPTY_SPACE);

        int[] values = new int[valuesString.length];

        int supplyValue = 0;
        int buyValue = 0;

        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(valuesString[i]);
        }

        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(SUPPLY)) {
                supplyValue += values[i];
            }
            if (names[i].equals(BUY)) {
                buyValue += values[i];
            }
        }

        int resultValue = supplyValue - buyValue;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(to))) {

            bufferedWriter.write(SUPPLY + COMMA + supplyValue + NEW_LINE
                                + BUY + COMMA + buyValue + NEW_LINE
                                + RESULT + COMMA + resultValue);

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
