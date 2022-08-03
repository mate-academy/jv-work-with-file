package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String REGEX = "\\D+";
    private int supplyCount = 0;
    private int buyCount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        getCounts(fromFileName);
        int resultCount = supplyCount - buyCount;
        writeToFile(toFileName,
                    "supply," + this.supplyCount + "\n"
                            + "buy," + this.buyCount + "\n"
                            + "result," + resultCount + "\n");
    }

    private void getCounts(String fromFileName) {
        if (fromFileName == null) {
            throw new RuntimeException("No such file" + fromFileName);
        }
        try {
            List<String> stringList = Files.readAllLines(Paths.get(fromFileName));
            for (String line : stringList) {
                if (line.startsWith("supply")) {
                    this.supplyCount += Integer.parseInt(line.replaceAll(REGEX, ""));
                } else if (line.startsWith("buy")) {
                    this.buyCount += Integer.parseInt(line.replaceAll(REGEX, ""));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file" + e);
        }
    }

    private void writeToFile(String toFileName, String value) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(value);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}
