package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] readValue = read(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        for (String s : readValue) {
            String tempValue = s.substring(0, s.indexOf(","));
            int tempNum = Integer.parseInt(s.substring(s.indexOf(",") + 1));
            if (map.containsKey(tempValue)) {
                Integer tempVal = map.get(tempValue);
                map.replace(tempValue, tempVal + tempNum);
            } else {
                map.put(tempValue, tempNum);
            }
        }
        Integer supplyNumValue = map.get(SUPPLY);
        Integer buyNumValue = map.get(BUY);
        stringBuilder.append(SUPPLY)
                .append(",")
                .append(supplyNumValue)
                .append(System.lineSeparator())
                .append(" ")
                .append(BUY)
                .append(",")
                .append(buyNumValue)
                .append(System.lineSeparator())
                .append(" ")
                .append("result,")
                .append(supplyNumValue - buyNumValue);
        write(toFileName, stringBuilder.toString().split(" "));
    }

    private String[] read(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int value = bufferedReader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = bufferedReader.read();
            }
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("Can't read data from the file: %s", fromFileName)
            );
        }
        return stringBuilder.toString().split("\n");
    }

    private void write(String toFileName, String[] toWrite) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String s : toWrite) {
                bufferedWriter.write(s);
            }
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("Can't write data to file: %s", toFileName)
            );
        }
    }
}
