package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringsFromFile = read(fromFileName);
        String report = createReport(stringsFromFile);
        write(report, toFileName);
    }

    private List<String> read(String fileName) {
        File sourceFile = new File(fileName);
        List<String> stringsFromSourceFile;
        try {
            stringsFromSourceFile = Files.readAllLines(sourceFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + sourceFile, e);
        }
        return stringsFromSourceFile;
    }

    private String createReport(List<String> strings) {
        Map<String, Integer> reportMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            String key = string.split(",")[0];
            int value = Integer.parseInt(string.split(",")[1]);
            if (!reportMap.containsKey(key)) {
                reportMap.put(key, value);
            } else {
                int temp = reportMap.get(key);
                reportMap.put(key, value + temp);
            }
        }

        int supply = reportMap.get("supply");
        int buy = reportMap.get("buy");
        stringBuilder.append("supply" + ",")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy" + ",")
                .append(buy)
                .append(System.lineSeparator())
                .append("result" + ",")
                .append(supply - buy);

        return stringBuilder.toString();
    }

    private void write(String report, String fileName) {
        File outputFile = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + outputFile, e);
        }
    }
}
