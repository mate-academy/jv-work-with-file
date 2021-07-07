package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            List<String> lines = Files.readAllLines(new File(fromFileName).toPath());
            int supply = 0;
            int buy = 0;
            for (String line : lines) {
                String[] dividedLine = line.split(SEPARATOR);
                if (dividedLine[0].equals("supply")) {
                    supply += Integer.parseInt(dividedLine[1]);
                } else {
                    buy += Integer.parseInt(dividedLine[1]);
                }
            }
            writeReport(supply, buy, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Could not read from file " + fromFileName, e);
        }
    }

    private void writeReport(int supplySum, int buySum, String toFileName) {
        String report = new StringBuilder("supply,")
                .append(supplySum)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buySum)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplySum - buySum)
                .toString();
        try {
            File fileToWrite = new File(toFileName);
            fileToWrite.createNewFile();
            Files.write(fileToWrite.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Could not write to file " + toFileName, e);
        }
    }
}
