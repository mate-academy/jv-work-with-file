package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMA = ",";

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
                if (dataInfo.contains(SUPPLY)) {
                    supplyResult += Integer.parseInt(dataInfo.split(COMA)[1]);
                } else {
                    buyResult += Integer.parseInt(dataInfo.split(COMA)[1]);
                }
            }
            stringBuilder.append(SUPPLY).append(COMA).append(supplyResult)
                    .append(System.lineSeparator()).append(BUY).append(COMA)
                    .append(buyResult).append(System.lineSeparator())
                    .append("result").append(COMA).append(supplyResult - buyResult);
        } catch (Exception e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
