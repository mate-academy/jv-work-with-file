package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String BUY_OPERATION = "buy";
    static final String SUPPLY_OPERATION = "supply";
    static final String COMMA_DELIMITER = ",";
    static final String LINE_BREAK_DELIMITER = "\n";
    static final String VOID_DELIMITER = "";
    static final String CARRIAGE_DELIMITER = "\r";
    static final int RESULT_SUPPLY_OPERATION = 0;
    static final int RESULT_BUY_OPERATION = 1;
    static final int RESULT_OPERATIONS = 2;
    static final int FIRST_STRING_SPLIT_LINE_ARGUMENT = 0;
    static final int SECOND_INT_SPLIT_LINE_ARGUMENT = 1;
    static final int RESULT_VALUES_LENGTH = 3;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        writeToFile(generateReport(dataFromFile), toFileName);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String valueFromFile = fileReader.readLine();
            while (valueFromFile != null) {
                stringBuilder.append(valueFromFile).append(System.lineSeparator());
                valueFromFile = fileReader.readLine();
            }
            return stringBuilder.toString().replace(CARRIAGE_DELIMITER, VOID_DELIMITER);
        } catch (IOException cat) {
            throw new RuntimeException("Can't open file" + cat);
        }
    }

    private int[] generateReport(String dataFromFile) {
        String[] dataStrings = dataFromFile.split(LINE_BREAK_DELIMITER);
        String[] splitLines;
        int[] resultsValues = new int[RESULT_VALUES_LENGTH];
        for (String line : dataStrings) {
            splitLines = line.split(COMMA_DELIMITER);
            if (splitLines[FIRST_STRING_SPLIT_LINE_ARGUMENT].equals(SUPPLY_OPERATION)) {
                resultsValues[RESULT_SUPPLY_OPERATION] += Integer
                        .valueOf(splitLines[SECOND_INT_SPLIT_LINE_ARGUMENT]);
            } else {
                resultsValues[RESULT_BUY_OPERATION] += Integer
                        .valueOf(splitLines[SECOND_INT_SPLIT_LINE_ARGUMENT]);
            }
        }
        resultsValues[RESULT_OPERATIONS] =
                resultsValues[RESULT_SUPPLY_OPERATION] - resultsValues[RESULT_BUY_OPERATION];
        return resultsValues;
    }

    private void writeToFile(int[] resultsValues, String toFileName) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            fileWriter.write(SUPPLY_OPERATION + COMMA_DELIMITER
                    + resultsValues[RESULT_SUPPLY_OPERATION] + System.lineSeparator());
            fileWriter.write(BUY_OPERATION + COMMA_DELIMITER
                    + resultsValues[RESULT_BUY_OPERATION] + System.lineSeparator());
            fileWriter.write("result" + COMMA_DELIMITER
                    + resultsValues[RESULT_OPERATIONS] + System.lineSeparator());
        } catch (IOException cat) {
            throw new RuntimeException("Can't write to file" + cat);
        }
    }
}
