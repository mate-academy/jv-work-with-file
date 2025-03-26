package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int EXPECTED_PARTS_LENGTH = 2;
    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String BUY_TYPE = "buy";
    private static final String SUPPLY_TYPE = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int sumBuy = 0;
        int sumSupply = 0;
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < EXPECTED_PARTS_LENGTH) {
                    throw new RuntimeException("Invalid line structure: " + line);
                }
                String type = parts[TYPE_INDEX].trim();
                if (!BUY_TYPE.equals(type) && !SUPPLY_TYPE.equals(type)) {
                    throw new RuntimeException("Invalid type '" + type + "' in line: " + line);
                }
                int value;
                try {
                    value = Integer.parseInt(parts[VALUE_INDEX].trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid number in line: " + line, e);
                }
                if (value < 0) {
                    throw new RuntimeException("Negative value in line: " + line);
                }
                if (BUY_TYPE.equals(type)) {
                    sumBuy += value;
                } else if (SUPPLY_TYPE.equals(type)) {
                    sumSupply += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        writeReport(toFileName, sumSupply, sumBuy);
    }

    private void writeReport(String toFileName, int sumSupply, int sumBuy) {
        File newFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            writer.write("supply," + sumSupply);
            writer.newLine();
            writer.write("buy," + sumBuy);
            writer.newLine();
            writer.write("result," + (sumSupply - sumBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }
}
