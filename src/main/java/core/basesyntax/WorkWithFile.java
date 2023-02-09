package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WorkWithFile {
    private static final int WORD = 0;
    private static final int VALUE = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT_VALUE = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        File fromFile = new File(fromFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile));
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            String resultList = makeDataList(bufferedReader);
            bufferedWriter.write(resultList);
        } catch (IOException e) {
            throw new RuntimeException("wrong file path");
        }
    }

    private String makeDataList(BufferedReader bufferedReader) throws IOException {
        StringBuilder list = new StringBuilder();
        HashMap<String, String> hashMap = getMapInformation(bufferedReader);
        list.append(SUPPLY).append(",").append(hashMap.get(SUPPLY))
                .append(System.lineSeparator());
        list.append(BUY).append(",").append(hashMap.get(BUY))
                .append(System.lineSeparator());
        list.append(RESULT_VALUE).append(",").append(hashMap.get(RESULT_VALUE));
        return list.toString();
    }

    private HashMap<String, String> getMapInformation(BufferedReader bufferedReader)
            throws IOException {
        HashMap<String, String> statistic = new HashMap<>();
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] splitLine = line.split(",");
            try {
                sortInformation(statistic, splitLine[WORD], splitLine[VALUE]);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("wrong file structure");
            }
            line = bufferedReader.readLine();
        }
        countResultValue(statistic);
        return statistic;
    }

    private void countResultValue(HashMap<String, String> statistic) {
        int resultValue = Integer.parseInt(statistic.get(SUPPLY))
                - Integer.parseInt(statistic.get(BUY));
        statistic.put(RESULT_VALUE, Integer.toString(resultValue));
    }

    private void sortInformation(HashMap<String, String> statistic,
                                 String name, String value) {
        if (statistic.containsKey(name)) {
            int sumOfValue = Integer.parseInt(value) + Integer.parseInt(statistic.get(name));
            statistic.put(name, Integer.toString(sumOfValue));
        } else {
            statistic.put(name, value);
        }
    }
}
