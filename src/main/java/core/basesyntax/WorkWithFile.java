package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WorkWithFile {
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String[] OUTPUT_ORDER = {"supply", "buy", "result"};

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> readData = readFromFile(fromFileName);
        Map<String, Integer> sortedData = sortFileData(readData);
        writeToFile(toFileName, sortedData);
    }

    private List<String> readFromFile(String fromFileName) {
        List<String> readData = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String newLine = bufferedReader.readLine();
            while (newLine != null) {
                readData.add(newLine);
                newLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return readData;
    }

    private Map<String, Integer> sortFileData(List<String> readData) {
        Map<String, Integer> sortedData = new TreeMap<>();
        for (String lineData : readData) {
            String[] splitLineData = lineData.split(COMMA);
            if (sortedData.get(splitLineData[OPERATION_TYPE_POSITION]) != null) {
                sortedData.put(splitLineData[OPERATION_TYPE_POSITION],
                        sortedData.get(splitLineData[OPERATION_TYPE_POSITION])
                        + Integer.parseInt(splitLineData[AMOUNT_POSITION]));
            } else {
                sortedData.put(splitLineData[OPERATION_TYPE_POSITION],
                        Integer.parseInt(splitLineData[1]));
            }
        }
        sortedData.put(RESULT, sortedData.get(SUPPLY) - sortedData.get(BUY));
        return sortedData;
    }

    private void writeToFile(String toFileName, Map<String, Integer> sortedData) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            StringBuilder statistic = new StringBuilder();
            for (String operationType : OUTPUT_ORDER) {
                statistic.append(operationType).append(COMMA).append(sortedData
                        .get(operationType)).append(System.lineSeparator());
            }
            bufferedWriter.write(statistic.toString().trim());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
