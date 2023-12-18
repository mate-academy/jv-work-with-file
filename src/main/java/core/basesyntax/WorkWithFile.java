package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> strings = getData(fromFileName);
        String reportString = dataProcessing(strings);
        createReport(toFileName, reportString);
    }

    private static String dataProcessing(List<String> strings) {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(SUPPLY, 0);
        linkedHashMap.put(BUY, 0);
        linkedHashMap.put(RESULT, 0);
        for (String string : strings) {
            String[] stringSplit = string.split(",");
            int value = linkedHashMap.get(stringSplit[0]) + Integer.parseInt(stringSplit[1]);
            linkedHashMap.put(stringSplit[0], value);
        }
        linkedHashMap.put(RESULT, (linkedHashMap.get(SUPPLY) - linkedHashMap.get(BUY)));
        StringBuilder stringBuilder = new StringBuilder();
        linkedHashMap.forEach((key, value) -> stringBuilder.append(key).append(",")
                .append(value).append(System.lineSeparator()));
        return stringBuilder.toString();
    }

    private static void createReport(String toFileName, String reportString) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(reportString);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }

    private static List<String> getData(String fromFileName) {
        File file = new File(fromFileName);
        List<String> strings = null;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }
}
