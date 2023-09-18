package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int NAME = 0;
    private static final int NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileContent = readFile(fromFileName);
        String report = getResult(fileContent);
        writeToFile(toFileName, report);
    }

    private static List<String> readFile(String fromFileName) {
        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data");
        }
    }

    private static String getResult(List<String> lines) {
        int supplySum = 0;
        int buySum = 0;
        for (String line : lines) {
            String [] parts = line.split(",");
            if (parts[NAME].equals("supply")) {
                supplySum += Integer.parseInt(parts[NUMBER]);
            }
            if (parts[NAME].equals("buy")) {
                buySum += Integer.parseInt(parts[NUMBER]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(supplySum).append(System.lineSeparator());
        builder.append("buy,").append(buySum).append(System.lineSeparator());
        builder.append("result,").append(result);
        String resultReport = builder.toString();
        return resultReport;

    }

    private void writeToFile(String toFileName, String resultReport) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data", e);
        }
    }
}
