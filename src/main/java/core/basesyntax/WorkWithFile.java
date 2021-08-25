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
        return sortedData;
    }

    private void writeToFile(String toFileName, Map<String, Integer> sortedData) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (int i = sortedData.keySet().size() - 1; i >= 0; i--) {
                bufferedWriter.write(sortedData.keySet().toArray()[i].toString());
                bufferedWriter.write(COMMA);
                bufferedWriter.write(sortedData.get(sortedData.keySet()
                                .toArray()[i].toString()).toString());
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.write(RESULT);
            bufferedWriter.write(COMMA);
            bufferedWriter.write("" + (sortedData.get(SUPPLY) - sortedData.get(BUY)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
