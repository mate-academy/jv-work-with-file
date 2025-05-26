package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int VALUE_INDEX = 1;
    private static final int TYPE_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        String[] lines = readFromFile(fromFileName);
        for (String line : lines) {
            String[] data = line.split(COMMA);
            String type = data[TYPE_INDEX];
            int amount = Integer.parseInt(data[VALUE_INDEX]);

            if (type.equals(SUPPLY)) {
                supplyTotal += amount;
            } else if (type.equals(BUY)) {
                buyTotal += amount;
            }
        }

        String report = createReport(supplyTotal, buyTotal);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
    }

    private void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
        return builder.toString();
    }
}
