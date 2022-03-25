package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final int AMOUNT_INDEX = 1;
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> records = readFile(fromFileName);
        writeFile(toFileName, getReportFrom(records));
    }

    private List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(new File(fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
    }

    private String getReportFrom(List<String> records) {
        int totalBuy = 0;
        int totalSupply = 0;
        int amount;
        for (String record : records) {
            amount = Integer.parseInt(record.split(COMMA)[AMOUNT_INDEX]);
            if (record.contains(OPERATION_BUY)) {
                totalBuy += amount;
            } else if (record.contains(OPERATION_SUPPLY)) {
                totalSupply += amount;
            }
        }
        int result = totalSupply - totalBuy;
        return createReport(totalBuy, totalSupply, result);
    }

    private String createReport(int totalBuy, int totalSupply, int result) {
        return new StringBuilder()
          .append(OPERATION_SUPPLY).append(COMMA).append(totalSupply).append(System.lineSeparator())
          .append(OPERATION_BUY).append(COMMA).append(totalBuy).append(System.lineSeparator())
          .append(OPERATION_RESULT).append(COMMA).append(result).toString();
    }

    private void writeFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + fileName, e);
        }
    }
}
