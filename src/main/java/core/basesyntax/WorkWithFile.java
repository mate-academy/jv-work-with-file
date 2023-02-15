package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int LOWEST_ITERATION_BORDER = -1;
    private static final String SPLITTER = "\n";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final boolean TRUE = true;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReport(readData(fromFileName));
        writeData(toFileName, report);
    }

    private String[] readData(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        String[] itemsArray;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();
            while (value != LOWEST_ITERATION_BORDER) {
                builder.append((char) value);
                value = reader.read();
            }
            String itemsAndQuantities = builder.toString();
            itemsArray = itemsAndQuantities.split(SPLITTER);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return itemsArray;
    }

    private String createReport(String[] data) {
        int buyTotal = 0;
        int supplyTotal = 0;
        for (String datum : data) {
            if (datum.split(COMMA)[INDEX_ZERO].equals(BUY)) {
                buyTotal += Integer.parseInt(datum.split(COMMA)[INDEX_ONE]);
            } else {
                supplyTotal += Integer.parseInt(datum.split(COMMA)[INDEX_ONE]);
            }
        }
        int result = supplyTotal - buyTotal;
        return (new StringBuilder(SUPPLY).append(COMMA).append(supplyTotal)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyTotal).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString());
    }

    private void writeData(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, TRUE))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data into file", e);
        }
    }
}
