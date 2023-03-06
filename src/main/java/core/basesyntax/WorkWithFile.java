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

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = buildReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String dataFromFile;
        try {
            dataFromFile = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return dataFromFile;
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file ", e);
        }
    }

    private String buildReport(String fileData) {
        String[] dataLines = fileData.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String line : dataLines) {
            String[] split = line.split(DATA_SEPARATOR);
            if (split[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                supplySum += Integer.parseInt(split[AMOUNT_INDEX]);
            } else if (split[OPERATION_INDEX].equals(BUY_OPERATION)) {
                buySum += Integer.parseInt(split[AMOUNT_INDEX]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION).append(DATA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(DATA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append("result").append(DATA_SEPARATOR).append(supplySum - buySum);
        return builder.toString();
    }
}
