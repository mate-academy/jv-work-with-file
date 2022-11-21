package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> map = getPositionsFromFile(fromFileName);
        int supply = map.get("supply");
        int buy = map.get("buy");
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
                int number = Integer.parseInt(dividedLine[1]);
                if (map.containsKey(dividedLine[0])) {
                    map.replace(dividedLine[0], map.get(dividedLine[0]) + number);
                } else {
                    map.put(dividedLine[0], number);
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file", e);
        }
    }
}
