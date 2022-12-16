package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int OPERATIONS_INDEX = 0;
    private static final int OPERATIONS_INDEX_VALUE = 1;
    private static final String PUNCTUATION_SYMBOL_COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFromFile(fromFileName);
        String resultReport = createReport(readData);
        writeFile(toFileName, resultReport);
    }

    private String readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder;
        int value;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            stringBuilder = new StringBuilder();
            value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e + fromFileName);
        }
        return stringBuilder.toString();
    }

    private void writeFile(String toFileName, String resultReport) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {
            bufferedWriter.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file" + e);
        }
    }

    private String createReport(String fromFileName) {
        StringBuilder resultString = new StringBuilder();
        String[] lines = fromFileName.split(System.lineSeparator());
        int buyCounter = 0;
        int supplyCounter = 0;
        for (int i = 0; i < lines.length; i++) {
            String[] operations = lines[i].split(PUNCTUATION_SYMBOL_COMMA);
            if (operations[OPERATIONS_INDEX].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(operations[OPERATIONS_INDEX_VALUE]);
            }
            if (operations[OPERATIONS_INDEX].equals(BUY)) {
                buyCounter += Integer.parseInt(operations[OPERATIONS_INDEX_VALUE]);
            }
        }
        int result = supplyCounter - buyCounter;
        return resultString.append(SUPPLY).append(PUNCTUATION_SYMBOL_COMMA).append(supplyCounter)
                .append(System.lineSeparator())
                .append(BUY).append(PUNCTUATION_SYMBOL_COMMA)
                .append(buyCounter)
                .append(System.lineSeparator())
                .append(RESULT).append(PUNCTUATION_SYMBOL_COMMA).append(result).toString();
    }
}
