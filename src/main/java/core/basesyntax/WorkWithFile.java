package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int FIRST_ELEMENT = 0;
    private static final int SECOND_ELEMENT = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readData(fromFileName);
        String report = createReport(data);
        writeData(toFileName, report);
    }

    private List<String> readData(String fromFileName) {
        List<String> data;
        try {
            data = Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return data;
    }

    private void writeData(String toFileName, String report) {
        try {
            Files.write(new File(toFileName).toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createReport(List<String> data) {
        int buySum = 0;
        int supplySum = 0;
        for (String item : data) {
            String[] splittedData = item.split(",");
            if (BUY.equals(splittedData[FIRST_ELEMENT])) {
                buySum += Integer.parseInt(splittedData[SECOND_ELEMENT]);
            } else if (SUPPLY.equals(splittedData[FIRST_ELEMENT])) {
                supplySum += Integer.parseInt(splittedData[SECOND_ELEMENT]);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(",").append(supplySum).append(System.lineSeparator())
                            .append(BUY).append(",").append(buySum).append(System.lineSeparator())
                            .append(RESULT).append(",").append(supplySum - buySum);
        return builder.toString();
    }
}
