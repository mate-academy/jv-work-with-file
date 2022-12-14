package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {

    private static final int BUY_INDEX = 0;
    private static final int SUPPLY_INDEX = 1;
    private static final String SUPPLY_LINE = "supply";
    private static final String BUY_LINE = "buy";
    private static final String SEPARATOR = ",";
    private static final String DIFFERENCE_BETWEEN_SUPPLY_AND_BUY = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = reportBuild(data);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private void writeFile(String toFileName, String dataWithReport) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), dataWithReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String reportBuild(String readData) {
        int sumOffSupply = 0;
        int sumOffBuy = 0;
        String[] data = readData.split(System.lineSeparator());
        for (String reportLine : data) {
            String[] lines = reportLine.split(SEPARATOR);
            if (lines[BUY_INDEX].equals(BUY_LINE)) {
                sumOffBuy += Integer.parseInt(lines[SUPPLY_INDEX]);
            } else if (lines[BUY_INDEX].equals(SUPPLY_LINE)) {
                sumOffSupply += Integer.parseInt(lines[SUPPLY_INDEX]);
            }
        }
        return SUPPLY_LINE + SEPARATOR + sumOffSupply
                + System.lineSeparator()
                + BUY_LINE + SEPARATOR + sumOffBuy
                + System.lineSeparator()
                + DIFFERENCE_BETWEEN_SUPPLY_AND_BUY + SEPARATOR
                + (sumOffSupply - sumOffBuy);
    }
}
