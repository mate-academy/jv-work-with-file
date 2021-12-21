package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int ABSTRACT_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistic = readLines(fromFileName);
        writeToFile(toFileName, buildReport(statistic[SUPPLY_INDEX], statistic[BUY_INDEX]));
    }

    private int[] readLines(String fileName) {
        String supplyStr = "supply";
        String buyStr = "buy";
        String comma = ",";
        File fromFile = new File(fileName);
        int supply = 0;
        int buy = 0;
        try {
            List<String> readLines = Files.readAllLines(fromFile.toPath());
            for (String lines : readLines) {
                String[] split = lines.split(comma);
                if (split[ABSTRACT_INDEX].equals(supplyStr)) {
                    supply += Integer.parseInt(split[AMOUNT_INDEX]);
                } else if (split[ABSTRACT_INDEX].equals(buyStr)) {
                    buy += Integer.parseInt(split[AMOUNT_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supply, buy};
    }

    private String buildReport(int supply, int buy) {
        String supplyStr = "supply";
        String buyStr = "buy";
        String result = "result";
        String comma = ",";
        StringBuilder builder = new StringBuilder();
        return builder
                .append(supplyStr)
                .append(comma)
                .append(supply)
                .append(System.lineSeparator())
                .append(buyStr)
                .append(comma)
                .append(buy)
                .append(System.lineSeparator())
                .append(result)
                .append(comma)
                .append(supply - buy).toString();
    }

    private void writeToFile(String fileName, String dataToSave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(dataToSave);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + fileName, e);
        }
    }
}

