package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int buySum = 0;
        int supplySum = 0;
        String[] data = readFromFile(fromFileName).replace(System.lineSeparator(), SEPARATOR)
                .split(SEPARATOR);
        String[] categories = {SUPPLY, BUY};
        for (int i = 0; i < data.length; i++) {
            if (categories[BUY_INDEX].equals(data[i])) {
                buySum += Integer.parseInt(data[i + 1]);
            }
            if (categories[SUPPLY_INDEX].equals(data[i])) {
                supplySum += Integer.parseInt(data[i + 1]);
            }
        }
        writeToFile(toFileName, createReport(supplySum, buySum));
    }

    private String writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
        return toFileName;
    }

    private String readFromFile(String fileName) {
        String fileData;
        try {
            fileData = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        return fileData;
    }

    private String createReport(int supplySum, int buySum) {
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY).append(SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append((supplySum - buySum)).toString();
    }
}
