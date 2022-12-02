package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final char COMMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = readFile(fromFileName);
        String calculatedData = calculateData(stringBuilder);
        writeCalculatedData(calculatedData, toFileName);

    }

    private void writeCalculatedData(String calculatedData, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(calculatedData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String calculateData(StringBuilder stringBuilder) {
        int supplySum = 0;
        int buySum = 0;
        int result = 0;
        String[] strings = stringBuilder.toString().split(System.lineSeparator());
        for (String string : strings) {
            String[] strings1 = string.split(String.valueOf(COMMA));
            if (strings1[0].equals(Category.values()[0].toString())) {
                buySum += Integer.parseInt(strings1[1]);
            } else {
                supplySum += Integer.parseInt(strings1[1]);
            }
        }
        result = supplySum - buySum;
        stringBuilder = new StringBuilder();
        return stringBuilder
                .append(Category.values()[2].toString()).append(COMMA).append(supplySum)
                .append(System.lineSeparator())
                .append(Category.values()[0].toString()).append(COMMA).append(buySum)
                .append(System.lineSeparator())
                .append(Category.values()[1].toString()).append(COMMA).append(result).toString();

    }

    private StringBuilder readFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder;
    }
}
