package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    private static final String SPLITTER = ",";
    private static final int OPERATION_NAME_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToCsvFile(toFileName, computeStatistic(readFromCsvFile(fromFileName)));
    }

    public String[][] readFromCsvFile(String fromFileName) {
        List<String> dataList;
        try {
            dataList = Files.readAllLines(Path.of(new File(fromFileName).getPath()));
            String[][] dataTable = new String[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                dataTable[i] = dataList.toArray(String[]::new)[i].split(SPLITTER);
            }
            return dataTable;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    public String computeStatistic(String[][] dataTable) {
        StringBuilder statistic = new StringBuilder();
        int ammount = 0;
        int result = 0;
        for (OperationType operation : OperationType.values()) {
            if (operation != OperationType.SUPPLY && operation != OperationType.BUY) {
                continue;
            }
            for (String[] singleData : dataTable) {
                if (singleData[OPERATION_NAME_INDEX].equals(operation.toString().toLowerCase())) {
                    ammount += Integer.parseInt(singleData[AMMOUNT_INDEX]);
                }
            }
            statistic.append(operation.toString().toLowerCase()).append(SPLITTER)
                    .append(ammount).append(System.lineSeparator());
            result = operation.equals(OperationType.SUPPLY) ? result + ammount : result - ammount;
            ammount = 0;
        }
        statistic.append(OperationType.RESULT.toString().toLowerCase())
                .append(SPLITTER).append(result);
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
