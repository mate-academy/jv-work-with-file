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
        int[] resultsValues = new int[3];
        for (String line : dataStrings) {
            splitLines = line.split(COMMA_DELIMITER);
            if (splitLines[0].equals(SUPPLY_OPERATION)) {
                resultsValues[0] += Integer.valueOf(splitLines[1]);
            } else {
                resultsValues[1] += Integer.valueOf(splitLines[1]);
            }
        }
        resultsValues[2] = resultsValues[0] - resultsValues[1];
        return resultsValues;
    }

    private void writeToFile(int[] resultsValues, String toFileName) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(toFileName))) {
            fileWriter.write(SUPPLY_OPERATION + COMMA_DELIMITER
                    + resultsValues[0] + System.lineSeparator());
            fileWriter.write(BUY_OPERATION + COMMA_DELIMITER
                    + resultsValues[1] + System.lineSeparator());
            fileWriter.write("result" + COMMA_DELIMITER
                    + resultsValues[2] + System.lineSeparator());
        } catch (IOException cat) {
            throw new RuntimeException("Can't write to file" + cat);
        }
    }
}
