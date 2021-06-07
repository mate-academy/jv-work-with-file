package core.basesyntax;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String COMMA = ",";
    public static final int INDEX_OF_OPERATION = 0;
    public static final int INDEX_OF_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] element;

        for (String rowCsv : getStringList(fromFileName)) {
            element = rowCsv.split(COMMA);
            if (map.get(element[INDEX_OF_OPERATION]) == null) {
                map.put(element[INDEX_OF_OPERATION], Integer.valueOf(element[INDEX_OF_VALUE]));
            } else {
                map.put(element[INDEX_OF_OPERATION], map.get(element[INDEX_OF_OPERATION])
                        + Integer.parseInt(element[INDEX_OF_VALUE]));
            }
        }
        writeToFile(toFileName, getReport(map));
    }

    private byte[] getReport(HashMap<String, Integer> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LINE_SEPARATOR).append(SUPPLY).append(COMMA).append(map.get(SUPPLY));
        stringBuilder.append(LINE_SEPARATOR).append(BUY).append(COMMA).append(map.get(BUY));
        stringBuilder.append(LINE_SEPARATOR).append(RESULT).append(COMMA)
            .append(map.get(SUPPLY) - map.get(BUY));
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }

    private List<String> getStringList(String fromFileName) {
        List<String> list;
        try {
            list = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("IO exception when reading the file " + fromFileName, e);
        }
        return list;
    }

    private void writeToFile(String toFileName, byte[] report) {
        try {
            Files.write(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException("IO exception when writing in the file " + toFileName, e);
        }
    }
}
