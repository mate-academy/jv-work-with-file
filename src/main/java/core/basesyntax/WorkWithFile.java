package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SPLITTER = ",";
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToCsvFile(toFileName, computeStatistic(readFromCsvFile(fromFileName)));
    }

    public String[] readFromCsvFile(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(new File(fromFileName).getPath()))
                    .toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public String computeStatistic(String[] dataTable) {
        StringBuilder statistic = new StringBuilder();
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String singleDataLine : dataTable) {
            String[] singleData = singleDataLine.split(SPLITTER);
            if (singleData[OPERATION_NAME_INDEX]
                    .equals(OperationType.SUPPLY.toString().toLowerCase())) {
                supplyAmount += Integer.parseInt(singleData[AMMOUNT_INDEX]);
            }
            if (singleData[OPERATION_NAME_INDEX]
                    .equals(OperationType.BUY.toString().toLowerCase())) {
                buyAmount += Integer.parseInt(singleData[AMMOUNT_INDEX]);
            }
        }
        statistic.append(OperationType.SUPPLY.toString().toLowerCase()).append(SPLITTER)
                .append(supplyAmount).append(System.lineSeparator())
                .append(OperationType.BUY.toString().toLowerCase()).append(SPLITTER)
                .append(buyAmount).append(System.lineSeparator())
                .append(OperationType.RESULT.toString().toLowerCase()).append(SPLITTER)
                .append(supplyAmount - buyAmount);
        return statistic.toString();
    }

    public void writeToCsvFile(String toFileName, String statistic) {
        try {
            Files.write(Path.of(new File(toFileName).getPath()),
                    statistic.getBytes(),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
