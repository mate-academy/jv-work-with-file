package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public static final int START_ACTION = 0;
    public static final int START_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(List<String> dataFromFile) {
        int supplies = 0;
        int buys = 0;
        int result;
        for (String content : dataFromFile) {
            String action = content.split(",")[START_ACTION];
            String sum = content.split(",")[START_SUM];
            if ("supply".equals(action)) {
                supplies += Integer.parseInt(sum);
            }
            if ("buy".equals(action)) {
                buys += Integer.parseInt(sum);
            }
        }
        result = supplies - buys;
        return "supply," + supplies + System.lineSeparator()
                + "buy," + buys + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private List<String> readFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }
}
