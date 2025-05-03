package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final int SUPPLY_COUNT = 0;
    public static final int BUY_COUNT = 1;
    public static final int ACTION_INFO = 0;
    public static final int ACTION_PRICE_INFO = 1;
    public static final int SIZE_OF_COUNT_ARRAY = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String incomingData = readFile(fromFileName);
        String report = getReport(incomingData);
        writeReport(report, toFileName);
    }

    private String getReport(String data) {
        int[] actionCount = new int[SIZE_OF_COUNT_ARRAY];
        String[] dataArray = data.split(" ");
        for (String lineInfo : dataArray) {
            String[] itemInfo = lineInfo.split(",");
            if (SUPPLY.equals(itemInfo[ACTION_INFO])) {
                actionCount[SUPPLY_COUNT] += Integer.parseInt(itemInfo[ACTION_PRICE_INFO]);
            } else if (BUY.equals(itemInfo[ACTION_INFO])) {
                actionCount[BUY_COUNT] += Integer.parseInt(itemInfo[ACTION_PRICE_INFO]);
            }

        }
        return String.format("%s,%d%s%s,%d%sresult,%d",
                SUPPLY, actionCount[SUPPLY_COUNT], System.lineSeparator(), BUY,
                actionCount[BUY_COUNT], System.lineSeparator(),
                actionCount[SUPPLY_COUNT] - actionCount[BUY_COUNT]);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder textFromFile = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                textFromFile.append(line).append(" ");
                line = reader.readLine();
            }
            return textFromFile.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }

    private void writeReport(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
