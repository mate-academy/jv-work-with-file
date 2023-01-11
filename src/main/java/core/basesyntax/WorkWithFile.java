package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkWithFile {
    public static final String SUPPLY_FIELD = "supply";
    public static final String BUY_FIELD = "buy";
    public static final String RESULT_FIELD = "result";
    public static final int NAME_INDEX = 0;
    public static final int VALUE_INDEX = 1;
    public static final String DELIMITER = ",";
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readDataFromFile(fromFileName);
        Map<String, Integer> parsedData = parseData(data);
        String report = createReport(parsedData);
        writeReportToFile(report, toFileName);
    }

    private void writeReportToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create the file " + toFileName, e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to  the file " + toFileName, e);
        }
    }

    private String createReport(Map<String, Integer> data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY_FIELD).append(DELIMITER).append(data.get(SUPPLY_FIELD))
                .append(System.lineSeparator())
                .append(BUY_FIELD).append(DELIMITER).append(data.get(BUY_FIELD))
                .append(System.lineSeparator())
                .append(RESULT_FIELD).append(DELIMITER)
                .append(data.get(SUPPLY_FIELD) - data.get(BUY_FIELD));
        return stringBuilder.toString();
    }

    private String readDataFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(LINE_SEPARATOR);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return stringBuilder.toString().replaceAll(LINE_SEPARATOR + "$","");
    }

    private Map<String, Integer> parseData(String stringData) {
        Map<String, Integer> result = new HashMap<>();
        result.put(SUPPLY_FIELD, 0);
        result.put(BUY_FIELD, 0);
        String[] data = stringData.split(LINE_SEPARATOR);
        for (String line: data) {
            String[] values = line.split(DELIMITER);
            String key = values[NAME_INDEX];
            result.put(key,result.get(key) + Integer.valueOf(values[VALUE_INDEX]));
        }
        return result;
    }
}
