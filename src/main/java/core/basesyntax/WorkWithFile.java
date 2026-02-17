package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] stats = readAndCalculate(fromFileName);
        String report = buildReport(stats);
        writeReport(toFileName, report);
    }

    private int[] readAndCalculate(String fromFileName) {
        int supplyValue = 0;
        int buyValue = 0;
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String type = parts[TYPE_INDEX];
                int amount = Integer.parseInt(parts[AMOUNT_INDEX]);
                if (SUPPLY.equals(type)) {
                    supplyValue += amount;
                } else if (BUY.equals(type)) {
                    buyValue += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }

        return new int[]{supplyValue, buyValue};
    }

    private String buildReport(int[] stats) {
        int supplyValue = stats[0];
        int buyValue = stats[1];
        int resultValue = supplyValue - buyValue;

        return SUPPLY + DELIMITER + supplyValue + System.lineSeparator()
                + BUY + DELIMITER + buyValue + System.lineSeparator()
                + RESULT + DELIMITER + resultValue;
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
