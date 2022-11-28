package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String DATA_SEPARATOR = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(DATA_SEPARATOR);
                if (data[0].equals(SUPPLY_OPERATION)) {
                    supplySum += Integer.parseInt(data[1]);
                } else if (data[0].equals(BUY_OPERATION)) {
                    buySum += Integer.parseInt(data[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFileName, e);
        }
        writeFile(supplySum, buySum, toFileName);
    }

    private void writeFile(int supplySum, int buySum, String toFileName) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION).append(DATA_SEPARATOR).append(supplySum)
                .append(System.lineSeparator())
                .append(BUY_OPERATION).append(DATA_SEPARATOR).append(buySum)
                .append(System.lineSeparator())
                .append(RESULT).append(DATA_SEPARATOR).append(supplySum - buySum);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
