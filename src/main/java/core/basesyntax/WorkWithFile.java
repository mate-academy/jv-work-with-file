package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY_STR = "supply";
    private static final String BUY_STR = "buy";
    private static final int OPERATION_INDEX = 0;
    private static final int NUMBER_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> map = getPositionsFromFile(fromFileName);
        int supply = map.get(SUPPLY_STR);
        int buy = map.get(BUY_STR);
        int result = supply - buy;
        writeToFile(supply, buy, result, toFileName);
    }

    private Map<String, Integer> getPositionsFromFile(String fromFileName) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            String[] dividedLine;
            
            while (line != null) {
                dividedLine = line.split(",");
                String operationType = dividedLine[OPERATION_INDEX];
                int number = Integer.parseInt(dividedLine[NUMBER_INDEX]);

                if (map.containsKey(operationType)) {
                    map.replace(operationType, map.get(operationType) + number);
                } else {
                    map.put(operationType, number);
                }
                line = reader.readLine();
            }
            return map;
        } catch (IOException e) {           
            throw new RuntimeException("Cannot read the file", e);
        } catch (NumberFormatException e1) {
            throw new RuntimeException("Input data has incorrect format", e1);
        }
    }

    private void writeToFile(int supply, int buy, int result, String toFileName) {
        StringBuilder builder = new StringBuilder();
        
        builder.append("supply,").append(supply).append(System.lineSeparator()).append("buy,")
                .append(buy).append(System.lineSeparator()).append("result,").append(result);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file", e);
        }
    }
}
