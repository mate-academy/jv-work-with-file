package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writerToFile(getReport(dataProcessing(readFile(fromFileName))), toFileName);
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String elementsInFile = reader.readLine();
            while (elementsInFile != null) {
                stringBuilder.append(elementsInFile).append(System.lineSeparator());
                elementsInFile = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    public Map<String, Integer> dataProcessing(String data) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put(SUPPLY, 0);
        result.put(BUY, 0);
        String[] operationData = data.split(System.lineSeparator());
        for (String str : operationData) {
            String[] values = str.split("\\W+");
            if (values[OPERATION_INDEX].equals(SUPPLY)) {
                result.put(values[OPERATION_INDEX],
                        result.get(values[OPERATION_INDEX])
                                + Integer.parseInt(values[AMOUNT_INDEX]));
            } else if (values[OPERATION_INDEX].equals(BUY)) {
                result.put(values[OPERATION_INDEX],
                        result.get(values[OPERATION_INDEX])
                                + Integer.parseInt(values[AMOUNT_INDEX]));
            }
        }
        return result;
    }

    public String getReport(Map<String, Integer> data) {
        int result = data.get(SUPPLY) - data.get(BUY);
        StringBuilder report = new StringBuilder("supply,")
                .append(data.get(SUPPLY))
                .append(System.lineSeparator())
                .append("buy,")
                .append(data.get(BUY))
                .append(System.lineSeparator())
                .append("result,")
                .append(result);
        return report.toString();
    }

    private void writerToFile(String result, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
