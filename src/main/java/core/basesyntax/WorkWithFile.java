package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String SPLITTER = ",";
    private static final String SUPPLY = "supply";
    private static final int STEP_COUNT = 2;

    public void getStatistic(String fromFileName, String toFileName) {

        writeToFile(toFileName, collectStatistics(readFromFile(fromFileName)));
    }

    public String collectStatistics(List<String> statistic) {
        int supplySum = 0;
        int buySum = 0;
        for (String dataString : statistic) {
            String[] data = dataString.split(SPLITTER);
            for (int i = 0; i < data.length; i += STEP_COUNT) {
                if (data[i].startsWith(SUPPLY)) {
                    supplySum += Integer.parseInt(data[i + 1]);
                } else {
                    buySum += Integer.parseInt(data[i + 1]);
                }
            }
        }
        return "supply," + supplySum + "\n" + "buy,"
                + buySum + "\n" + "result,"
                + (supplySum - buySum);
    }

    public List<String> readFromFile(String fromFileName) {
        ArrayList<String> dataList = new ArrayList<>();
        String readString = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((readString = reader.readLine()) != null) {
                dataList.add(readString);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open file to read", e);
        }
        return dataList;
    }

    public void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't open file to write", e);
        }
    }
}

