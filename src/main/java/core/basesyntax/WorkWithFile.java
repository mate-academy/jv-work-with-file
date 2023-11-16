package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLIED = "supply";
    private static final String BOUGHT = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        String line;
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                dataFromFile.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return dataFromFile.toString();
    }

    private String createReport(String dataFromFile) {
        StringBuilder report = new StringBuilder();
        String[] lines = dataFromFile.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] words = line.split(",");
            if (words[0].equals(SUPPLIED)) {
                supply += Integer.parseInt(words[1]);
            } else if (words[0].equals(BOUGHT)) {
                buy += Integer.parseInt(words[1]);
            }
        }
        return report.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
