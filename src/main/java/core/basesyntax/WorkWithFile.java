package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(readFromFile(fromFileName)), toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        String[] dataFromFile;
        try {
            dataFromFile = Files.readString(Path.of(fromFileName))
                    .split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return dataFromFile;
    }

    private String createReport(String[] data) {
        int supplyResult = 0;
        int buyResult = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (String dataInfo : data) {
                String[] values = dataInfo.split(CSV_SEPARATOR);
                if (values[0].contains(SUPPLY_OPERATION)) {
                    supplyResult += Integer.parseInt(values[1]);
                } else {
                    buyResult += Integer.parseInt(values[1]);
                }
            }
            stringBuilder.append(SUPPLY_OPERATION).append(CSV_SEPARATOR).append(supplyResult)
                    .append(System.lineSeparator()).append(BUY_OPERATION).append(CSV_SEPARATOR)
                    .append(buyResult).append(System.lineSeparator())
                    .append("result").append(CSV_SEPARATOR).append(supplyResult - buyResult);
        } catch (Exception e) {
            throw new RuntimeException("Can't create report", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
