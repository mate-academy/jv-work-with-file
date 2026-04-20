package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY_LABEL = "supply";
    private static final String BUY_LABEL = "buy";
    private static final String RESULT_LABEL = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(toFileName, prepareDataForWriting(fromFileName));
    }

    private Map<String, Integer> getResultData(List<String> inputData) {
        Map<String, Integer> resultData = new HashMap<>();
        for (String line : inputData) {
            String[] separatedData = line.split(",");
            String operation = separatedData[0];
            int amount = Integer.parseInt(separatedData[1]);
            resultData.put(operation, resultData.getOrDefault(operation, 0) + amount);
        }
        return resultData;
    }

    private List<String> readDataFromFile(String fromFileName) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, ex);
        }
        return result;
    }

    private String prepareDataForWriting(String fromFileName) {
        Map<String, Integer> data = getResultData(readDataFromFile(fromFileName));
        return SUPPLY_LABEL + "," + data.getOrDefault(SUPPLY_LABEL, 0) + "\n"
                + BUY_LABEL + "," + data.getOrDefault(BUY_LABEL, 0) + "\n"
                + RESULT_LABEL + ","
                + (data.getOrDefault(SUPPLY_LABEL, 0) - data.getOrDefault(BUY_LABEL, 0));
    }

    private void writeDataToFile(String toFileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write data to file: " + toFileName, ex);
        }
    }
}
