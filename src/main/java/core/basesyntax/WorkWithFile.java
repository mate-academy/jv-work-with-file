package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INNER_ARRAY_SIZE = 2;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_TWO = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String[][] data = readFromFile(fromFileName);
        int[] statistics = calculateReport(data);
        writeToFile(toFileName, statistics);
    }

    private String[][] readFromFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int arraySize = createArraySize(fileName);
            String[][] sourceArray = new String[arraySize][INNER_ARRAY_SIZE];
            String line;
            int i = NUMBER_ZERO;
            while ((line = bufferedReader.readLine()) != null) {
                sourceArray[i] = line.split(COMMA);
                i++;
            }
            return sourceArray;
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }

    private int[] calculateReport(String[][] originalSourceArray) {
        int totalSupply = NUMBER_ZERO;
        int totalBuy = NUMBER_ZERO;
        for (String[] sourceElement : originalSourceArray) {
            if (sourceElement[NUMBER_ZERO].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(sourceElement[NUMBER_ONE]);
            } else if (sourceElement[NUMBER_ZERO].equals(BUY)) {
                totalBuy += Integer.parseInt(sourceElement[NUMBER_ONE]);
            }
        }
        return new int[]{totalSupply, totalBuy, totalSupply - totalBuy};
    }

    private void writeToFile(String reportName, int[] statistics) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportName))) {
            bufferedWriter.write(buildReportContent(statistics));
        } catch (IOException e) {
            throw new RuntimeException("Can't write info in file", e);
        }
    }

    private String buildReportContent(int[] statistics) {
        StringBuilder text = new StringBuilder();
        text.append(SUPPLY).append(COMMA).append(statistics[NUMBER_ZERO])
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(statistics[NUMBER_ONE])
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(statistics[NUMBER_TWO]);
        return text.toString();
    }

    private int createArraySize(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            int arraySize = NUMBER_ZERO;
            while (bufferedReader.readLine() != null) {
                arraySize++;
            }
            return arraySize;
        } catch (IOException e) {
            throw new RuntimeException("Can't read info from file", e);
        }
    }
}
