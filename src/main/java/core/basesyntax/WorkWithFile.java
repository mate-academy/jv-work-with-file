package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String RECORD_SEPARATOR = ",";
    private static final int ITEM_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = createReport(data);
        writeReport(toFileName, report);
    }

    private String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
    }

    private String createReport(String data) {
        int totalSupply = 0;
        int totalBuy = 0;
        String[] rows = data.split(System.lineSeparator());
        for (String row : rows) {
            String[] record = row.split(RECORD_SEPARATOR);
            if (record[ITEM_INDEX].equals(SUPPLY_NAME)) {
                totalSupply += Integer.parseInt(record[VALUE_INDEX]);
            }
            if (record[ITEM_INDEX].equals(BUY_NAME)) {
                totalBuy += Integer.parseInt(record[VALUE_INDEX]);
            }
        }
        return SUPPLY_NAME + RECORD_SEPARATOR + totalSupply + System.lineSeparator()
                + BUY_NAME + RECORD_SEPARATOR + totalBuy + System.lineSeparator()
                + "result" + RECORD_SEPARATOR + (totalSupply - totalBuy);
    }

    private void writeReport(String toFileName, String report) {
        try {
            File statistic = new File(toFileName);
            Files.write(statistic.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
