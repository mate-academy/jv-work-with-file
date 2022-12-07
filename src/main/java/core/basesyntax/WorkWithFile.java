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
        String fileData = readFile(fromFileName);
        String report = buildReport(fileData);
        writeToFile(toFileName, report);
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

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

    private String buildReport(String fileData) {
        String[] dataLines = fileData.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : dataLines) {
            String[] splittedLine = line.split(DATA_SEPARATOR);
            if (splittedLine[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            } else if (splittedLine[OPERATION_INDEX].equals(BUY_OPERATION)) {
                buySum += Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY_OPERATION).append(DATA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(DATA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(DATA_SEPARATOR).append(supplySum - buySum);
        return reportBuilder.toString();
    }
}
