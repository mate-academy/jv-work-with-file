package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] lines = readFromFile(fromFileName).split(System.lineSeparator());
        int supplyCount = countSpecificType(lines, SUPPLY);
        int buyCount = countSpecificType(lines, BUY);
        int resultCount = supplyCount - buyCount;
        writeToFile(toFileName, prepareResult(supplyCount, buyCount, resultCount));
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from given file", e);
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String textToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(textToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to given file", e);
        }
    }

    private String prepareResult(int supplyCount, int buyCount, int resultCount) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(SEPARATOR).append(supplyCount).append(System.lineSeparator());
        builder.append(BUY).append(SEPARATOR).append(buyCount).append(System.lineSeparator());
        builder.append(RESULT).append(SEPARATOR).append(resultCount).append(System.lineSeparator());
        return builder.toString();
    }

    private int countSpecificType(String[] data, String type) {
        if (type == null || data == null) {
            return 0;
        }
        int count = 0;
        for (String entry : data) {
            String[] line = entry.split(SEPARATOR);
            if (line.length == 2 && line[0].equals(type)) {
                count += Integer.parseInt(line[1]);
            }
        }
        return count;
    }
}
