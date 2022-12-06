package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String DATA_SEPARATOR = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        String[] fileData = readFile(fromFileName).split(System.lineSeparator());
        for (String line : fileData) {
            String[] splitLine = line.split(DATA_SEPARATOR);
            if (splitLine[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            } else if (splitLine[OPERATION_INDEX].equals(BUY_OPERATION)) {
                buySum += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        String report = buildReport(supplySum, buySum);
        writeFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        String fileData;
        try {
            fileData = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        return fileData;
    }

    private void writeFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String buildReport(int supplySum, int buySum) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY_OPERATION).append(DATA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(DATA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(DATA_SEPARATOR).append(supplySum - buySum);
        return reportBuilder.toString();
    }
}
