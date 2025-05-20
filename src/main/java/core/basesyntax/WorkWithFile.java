package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkWithFile {
    private static final String COMMA_DELIMITER = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(prepareData(calculate(readFromFile(fromFileName))), toFileName);
    }

    public Map<String, List<Integer>> readFromFile(String fileName) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put(SUPPLY, new ArrayList<>());
        map.put(BUY, new ArrayList<>());

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                map.get(values[0]).add(Integer.valueOf(values[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public Map<String, Integer> calculate(Map<String, List<Integer>> map) {
        Map<String, Integer> result = new HashMap<>();
        result.put(SUPPLY, map.get(SUPPLY).stream().mapToInt(Integer::intValue).sum());
        result.put(BUY, map.get(BUY).stream().mapToInt(Integer::intValue).sum());
        result.put(RESULT, result.get(SUPPLY) - result.get(BUY));
        return result;
    }

    public List<String[]> prepareData(Map<String, Integer> map) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{SUPPLY, String.valueOf(map.get(SUPPLY))});
        data.add(new String[]{BUY, String.valueOf(map.get(BUY))});
        data.add(new String[]{RESULT, String.valueOf(map.get(RESULT))});
        return data;
    }

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "output.txt");
    }

    public String convertToCsv(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void writeToFile(List<String[]> dataLines, String fileName) {
        File csvOutputFile = new File(fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCsv)
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("'")) {
            escapedData = escapedData.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
}
