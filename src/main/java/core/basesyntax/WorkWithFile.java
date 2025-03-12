package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY_ACTION = "supply";
    private static final String BUY_ACTION = "buy";
    private static final String RESULT_ACTION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String fruitStatistic = readFile(fromFileName);
        String report = createReport(fruitStatistic);
        writeFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String fruitStatistic) {
        int supplyResult = 0;
        int buyResult = 0;
        String[] statisticRecords = fruitStatistic.split(System.lineSeparator());
        for (String record : statisticRecords) {
            String[] splittedRecord = record.split(COMMA);
            if (splittedRecord[0].equals(SUPPLY_ACTION)) {
                supplyResult += Integer.parseInt(splittedRecord[1]);
            } else if (splittedRecord[0].equals(BUY_ACTION)) {
                buyResult += Integer.parseInt(splittedRecord[1]);
            } else {
                throw new RuntimeException("Can't parse data: " + splittedRecord[0]);
            }
        }
        int result = supplyResult - buyResult;
        StringBuilder reportBuilder = new StringBuilder(SUPPLY_ACTION).append(COMMA)
                .append(supplyResult).append(System.lineSeparator())
                .append(BUY_ACTION).append(COMMA).append(buyResult)
                .append(System.lineSeparator())
                .append(RESULT_ACTION).append(COMMA).append(result);
        return reportBuilder.toString();
    }

    private void writeFile(String report, String toFileName) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
