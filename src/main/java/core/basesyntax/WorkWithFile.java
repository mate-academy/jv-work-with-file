package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static String SUPPLY_TEXT = "supply";
    private static String BUY_TEXT = "buy";
    private static String RESULT_TEXT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName);
        String statistic = calculateStatistic(data);
        writeInFile(toFileName, statistic);
    }

    private String[] readFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file - " + fileName, e);
        }
    }

    private String calculateStatistic(String... data) {
        int supply = 0;
        int buy = 0;
        for (String line : data) {
            String[] arguments = line.split(",");
            int lineValue = Integer.parseInt(arguments[1]);
            if (arguments[0].equals(SUPPLY_TEXT)) {
                supply += lineValue;
            } else {
                buy += lineValue;
            }
        }
        return createReport(supply, buy);
    }

    private String createReport(int supply, int buy) {
        String report = new StringBuilder()
                .append(SUPPLY_TEXT).append(",").append(supply).append(System.lineSeparator())
                .append(BUY_TEXT).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT_TEXT).append(",").append(supply - buy)
                .toString();
        return report;
    }

    private void writeInFile(String fileName, String statistic) {
        try {
            Files.write(Path.of(fileName), statistic.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data in file - " + fileName, e);
        }
    }
}
