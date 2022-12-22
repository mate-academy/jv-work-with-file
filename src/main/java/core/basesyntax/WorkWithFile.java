package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final int INDEX_OF_OPERATION = 0;
    private static final int INDEX_OF_QUANTITY = 1;
    private static final String SEPARATOR = ",";

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
            throw new RuntimeException("Can't read file: " + fromFileName, e);
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
            String[] operations = lines[i].split(SEPARATOR);
            if (operations[INDEX_OF_OPERATION].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(operations[INDEX_OF_QUANTITY]);
            }
            if (operations[INDEX_OF_OPERATION].equals(BUY)) {
                buyCounter += Integer.parseInt(operations[INDEX_OF_QUANTITY]);
            }
        }
        int result = supplyCounter - buyCounter;
        return resultString.append(SUPPLY).append(SEPARATOR).append(supplyCounter)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR)
                .append(buyCounter)
                .append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(result).toString();
    }
}
