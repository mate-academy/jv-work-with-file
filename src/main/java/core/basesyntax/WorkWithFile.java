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
        String[] dataFromFile = read(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Integer> map = new HashMap<>();
        for (String s : dataFromFile) {
            String operationType = s.substring(0, s.indexOf(","));
            int amount = Integer.parseInt(s.substring(s.indexOf(",") + 1));
            if (map.containsKey(operationType)) {
                Integer oldAmount = map.get(operationType);
                map.replace(operationType, oldAmount + amount);
            } else {
                map.put(operationType, amount);
            }
        }
        Integer totalSupply = map.get(SUPPLY);
        Integer totalBuy = map.get(BUY);
        stringBuilder.append(SUPPLY)
                .append(",")
                .append(totalSupply)
                .append(System.lineSeparator())
                .append(" ")
                .append(BUY)
                .append(",")
                .append(totalBuy)
                .append(System.lineSeparator())
                .append(" ")
                .append("result,")
                .append(totalSupply - totalBuy);
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
