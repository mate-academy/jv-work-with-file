package core.basesyntax;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;

    public void createReport(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = getReport(dataFromFile);
        writeFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        Path path = Path.of(fromFileName);
        try {
            String fileData = Files.readString(path);
            return fileData;
        } catch (IOException e) {
            throw new RuntimeException("File not found or cannot be read", e);
        }
    }

    private String getReport(String dataFromFile) {
        String[] splittedData = dataFromFile.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : splittedData) {
            String[] splittedLine = line.split(",");
            String operationType = splittedLine[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(splittedLine[AMOUNT_INDEX]);
            if (operationType.equals(SUPPLY)) {
                supplyAmount += amount;
            } else {
                buyAmount += amount;
            }
        }
        return getStatistic(supplyAmount, buyAmount);
    }

    private String getStatistic(int supplyAmount, int buyAmount) {
        int resultAmount = supplyAmount - buyAmount;
        StringBuilder statistic = new StringBuilder(SUPPLY)
                .append(",")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY)
                .append(",")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(",")
                .append(resultAmount);
        return statistic.toString();
    }

    private void writeFile(String statistic, String toFileName) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
