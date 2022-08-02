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
        String[] data = fileString(fromFileName).replace("\n", SEPARATOR).split(SEPARATOR);
        String[] categories = {SUPPLY, BUY};
        int buySum = 0;
        int supplySum = 0;
        for (int i = 0; i < data.length; i++) {
            if (categories[BUY_INDEX].equals(data[i])) {
                buySum += Integer.parseInt(data[i + 1]);
            }
            if (categories[SUPPLY_INDEX].equals(data[i])) {
                supplySum += Integer.parseInt(data[i + 1]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(supplySum).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buySum).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append((supplySum - buySum));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(String.valueOf(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String fileString(String fileName) {
        String fileData;
        try {
            fileData = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return fileData;
    }
}
