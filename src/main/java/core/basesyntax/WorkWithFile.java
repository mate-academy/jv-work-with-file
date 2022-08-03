package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String REGEX = "\\D+";
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] resultCount = getCounts(fromFileName);
        StringBuilder statistic = new StringBuilder();
        writeToFile(
                toFileName, String.valueOf(statistic.append(System.lineSeparator())
                        .append("supply,")
                        .append(resultCount[0])
                        .append(System.lineSeparator())
                        .append("buy,")
                        .append(resultCount[1])
                        .append(System.lineSeparator())
                        .append("result,")
                        .append(resultCount[2])
                        .append(System.lineSeparator())));
    }

    private int[] getCounts(String fromFileName) {
        if (fromFileName == null) {
            throw new RuntimeException("No such file " + fromFileName);
        }
        int[] result = new int[3];
        try {
            List<String> stringList = Files.readAllLines(Paths.get(fromFileName));
            for (String line : stringList) {
                if (line.startsWith("supply")) {
                    result[SUPPLY_INDEX]
                            += Integer.parseInt(line.replaceAll(REGEX, ""));
                } else if (line.startsWith("buy")) {
                    result[BUY_INDEX]
                            += Integer.parseInt(line.replaceAll(REGEX, ""));
                }
            }
            result[RESULT_INDEX] = result[0] - result[1];
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file " + fromFileName + e);
        }
        return result;
    }

    private void writeToFile(String toFileName, String value) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(value);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file " + toFileName + e);
        }
    }
}
