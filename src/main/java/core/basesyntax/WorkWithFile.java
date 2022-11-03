package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final int INDEX_OF_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataRead = readFromFile(fromFileName);
        StringBuilder report = createReport(dataRead);
        writeReport(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        String stringFromFile = null;
        try {
            stringFromFile = new String(Files.readAllBytes(Paths.get(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can't read files", e);
        }
        return stringFromFile;
    }

    private StringBuilder createReport(String data) {
        String[] dataSeparate = data.split(System.lineSeparator());
        String supply = "supply";
        int supplySum = 0;
        String buy = "buy";
        int buySum = 0;
        String result = "result";
        for (int i = 0; i < dataSeparate.length; i++) {
            if (dataSeparate[i].contains("supply")) {
                supplySum = supplySum + Integer.parseInt(dataSeparate[i]
                        .split(",")[INDEX_OF_NUMBER]);
            }
            if (dataSeparate[i].contains("buy")) {
                buySum = buySum + Integer.parseInt(dataSeparate[i]
                        .split(",")[INDEX_OF_NUMBER]);
            }
        }
        int resultTotal = supplySum - buySum;
        StringBuilder returnResult = new StringBuilder();
        return returnResult.append(supply).append(",").append(supplySum)
                .append(System.lineSeparator())
                .append(buy).append(",").append(buySum).append(System.lineSeparator())
                .append(result).append(",").append(resultTotal);
    }

    public void writeReport(StringBuilder data, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(toFileName))) {
            writer.write(String.valueOf(data));
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);

        }
    }
}
