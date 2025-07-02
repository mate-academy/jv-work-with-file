package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyTotal = 0;
        int buyTotal = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(COMMA);
                String operation = parts[OPERATION_INDEX];
                int amount = Integer.parseInt(parts[VALUE_INDEX]);

                if (operation.equals(SUPPLY)) {
                    supplyTotal += amount;
                } else if (operation.equals(BUY)) {
                    buyTotal += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }

        writeReport(toFileName, supplyTotal, buyTotal);
    }

    private void writeReport(String fileName, int supply, int buy) {
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(NEW_LINE);
        builder.append(BUY).append(COMMA).append(buy).append(NEW_LINE);
        builder.append("result").append(COMMA).append(result);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + fileName, e);
        }
    }
}
