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
    private static final int RESULT_VALUE = 2;
    private static final String EMPTY_SPACE = " ";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String COMMA = ",";
    private static final String SPECIAL_SIGN = "@";

    public void getStatistic(String fromFileName, String toFileName) {

        String readData = readDataFromFile(fromFileName);
        String[] returnDataSplitted = readData.split(SPECIAL_SIGN);

        String[] names = returnDataSplitted[TYPE].split(EMPTY_SPACE);
        String[] valuesString = returnDataSplitted[VALUE].split(EMPTY_SPACE);

        int[] values = new int[valuesString.length];

        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(valuesString[i]);
        }

        int[] numbers = calculateStatistic(names, values);

        String message = SUPPLY + COMMA + numbers[TYPE] + NEW_LINE
                + BUY + COMMA + numbers[VALUE] + NEW_LINE
                + RESULT + COMMA + numbers[RESULT_VALUE];

        writeReportToFile(toFileName, message);
    }

    private String readDataFromFile(String nameFile) {
        File from = new File(nameFile);

        StringBuilder namesBeforeSplit = new StringBuilder();
        StringBuilder valueBeforeSplit = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(from))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedLine = line.split(COMMA);

                if (splittedLine.length != 2) {
                    throw new RuntimeException("Input file is incorrect");
                }

                namesBeforeSplit.append(splittedLine[TYPE]).append(EMPTY_SPACE);
                valueBeforeSplit.append(splittedLine[VALUE]).append(EMPTY_SPACE);
            }
            if (namesBeforeSplit.isEmpty() && valueBeforeSplit.isEmpty()) {
                throw new RuntimeException("Input file is empty");
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        String result = namesBeforeSplit + SPECIAL_SIGN + valueBeforeSplit;
        return result;
    }

    private void writeReportToFile(String file, String message) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {

            bufferedWriter.write(message);

        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private int[] calculateStatistic(String[] names, int[] values) {

        int supplyValue = 0;
        int buyValue = 0;

        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(SUPPLY)) {
                supplyValue += values[i];
            }
            if (names[i].equals(BUY)) {
                buyValue += values[i];
            }
        }

        int resultValue = supplyValue - buyValue;

        int[] numbers = new int[] {supplyValue, buyValue, resultValue};

        return numbers;
    }
}
