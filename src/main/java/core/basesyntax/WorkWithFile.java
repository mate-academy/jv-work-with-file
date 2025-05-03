package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Map<String, Integer> hashMap = new LinkedHashMap();
        hashMap.put("supply",0);
        hashMap.put("buy",0);
        hashMap.put("result",0);

        List<String> informationByFile = getInformationByFile(fromFileName);

        for (String string : informationByFile) {
            String[] arrayConst = string.split(",");
            if (hashMap.containsKey(arrayConst[0])) {
                hashMap.put(arrayConst[0], hashMap.get(arrayConst[0])
                        + Integer.valueOf(arrayConst[1]));
            } else {
                hashMap.put(arrayConst[0], Integer.valueOf(arrayConst[1]));
            }
        }
        hashMap.put("result", hashMap.get("supply") - hashMap.get("buy"));
        saveToFile(toFileName, getReport(hashMap));
    }

    private List<String> getInformationByFile(String fromFileName) {
        File file = new File(fromFileName);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("File does not exist: " + fromFileName, e);
        }
    }

    private String getReport(Map<String, Integer> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        hashMap.forEach((key, value) -> stringBuilder
                .append(key).append(",")
                .append(value).append(System.lineSeparator()));

        return stringBuilder.toString();
    }

    private void saveToFile(String toFileName, String string) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file: " + toFileName, e);
        }

        try {
            Files.write(Paths.get(file.getPath()), Collections.singleton(string));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file: " + toFileName, e);
        }
    }
}
