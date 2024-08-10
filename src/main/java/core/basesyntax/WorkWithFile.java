package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int STARTING_COUNT = 0;
    private static final int ELEMENTS_ON_THE_LINE = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readData(fromFileName);
        int[] results = processStatistics(data);
        writeResults(toFileName, results);
    }

    private String[] readData(String fromFileName) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                data.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Was unable to find the file: ", e);
        } catch (IOException e) {
            throw new RuntimeException("Encountered an error while processing this file: ", e);
        }

        return data.toString().split(System.lineSeparator());
    }

    private int[] processStatistics(String[] data) {
        int supplyCount = STARTING_COUNT;
        int buyCount = STARTING_COUNT;

        for (String value : data) {
            String[] currentLine = value.split(SEPARATOR);
            if (currentLine.length == ELEMENTS_ON_THE_LINE) {
                int count = Integer.parseInt(currentLine[1]);
                if (currentLine[0].contains(SUPPLY)) {
                    supplyCount += count;
                } else if (currentLine[0].contains(BUY)) {
                    buyCount += count;
                }
            }
        }

        int result = supplyCount - buyCount;
        return new int[]{supplyCount, buyCount, result};
    }

    private void writeResults(String toFileName, int[] results) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(SUPPLY + SEPARATOR + results[0] + System.lineSeparator());
            bufferedWriter.write(BUY + SEPARATOR + results[1] + System.lineSeparator());
            bufferedWriter.write(RESULT + SEPARATOR + results[2]);
        } catch (IOException e) {
            throw new RuntimeException("Encountered an error while writing to the file: ", e);
        }
    }
}
