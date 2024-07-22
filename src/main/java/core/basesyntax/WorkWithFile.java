package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] textFromFile = readFromFile(fromFileName);
        String report = generateReport(textFromFile);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        try {
            return Files.readString(Path.of(fromFileName)).split("\r?\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private String generateReport(String[] data) {
        StringBuilder report = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        int result;
        for (String product : data) {
            String[] line = product.split(COMMA);
            if (line[0].equals("supply")) {
                supplySum += Integer.parseInt(line[1]);
            } else {
                buySum += Integer.parseInt(line[1]);
            }
        }
        result = supplySum - buySum;
        report.append("supply,").append(supplySum).append(System.lineSeparator());
        report.append("buy,").append(buySum).append(System.lineSeparator());
        report.append("result,").append(result);

        return report.toString();
    }

    private void writeToFile(String text, String toFile) {

        try (FileWriter file = new FileWriter(toFile);
                BufferedWriter writer = new BufferedWriter(file)) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
