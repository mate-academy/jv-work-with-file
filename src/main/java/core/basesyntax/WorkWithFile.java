package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final int POSITION_OF_OPERATION = 0;
    private static final int POSITION_OF_NUMBER = 1;
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";
    private static final String NAME_RESULT = "result";
    private static final String REGEX = ",";
    private static final char COMMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> dataFromFile = countDataFromFile(fromFileName);
        int supply = dataFromFile.getOrDefault(NAME_SUPPLY, 0);
        int buy = dataFromFile.getOrDefault(NAME_BUY, 0);
        writeDataToFile(supply, buy, toFileName);
    }

    private Map<String, Integer> countDataFromFile(String fromFileName) {
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String lineWithData = reader.readLine();
            while (lineWithData != null) {
                String[] splitLine = lineWithData.split(REGEX);
                if (splitLine.length == 2) {
                    map.merge(splitLine[POSITION_OF_OPERATION],
                            Integer.valueOf(splitLine[POSITION_OF_NUMBER]),
                            Integer::sum);
                }
                lineWithData = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't found the file: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read date from the file: " + fromFileName, e);
        }
        return map;
    }

    private void writeDataToFile(int howMuchSupply, int howMuchBuy, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            int result = howMuchSupply - howMuchBuy;
            StringBuilder builder = new StringBuilder();
            builder.append(NAME_SUPPLY)
                    .append(COMMA)
                    .append(howMuchSupply)
                    .append(System.lineSeparator());

            builder.append(NAME_BUY)
                    .append(COMMA)
                    .append(howMuchBuy)
                    .append(System.lineSeparator());

            builder.append(NAME_RESULT)
                    .append(COMMA)
                    .append(result)
                    .append(System.lineSeparator());
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
