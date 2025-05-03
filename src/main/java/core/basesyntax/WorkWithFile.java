package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    private int[] readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supplyTotal = 0;
            int buyTotal = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(DELIMITER);
                String operationType = fields[OPERATION_INDEX].trim();
                int amount = Integer.parseInt(fields[VALUE_INDEX].trim());
                if (operationType.equals("supply")) {
                    supplyTotal += amount;
                } else if (operationType.equals("buy")) {
                    buyTotal += amount;
                }
            }
            int result = supplyTotal - buyTotal;
            return new int[]{supplyTotal, buyTotal, result};

        } catch (IOException e) {
            throw new RuntimeException("Can't open the file " + fromFileName + " ", e);
        }
    }

    private void writeToFile(String toFileName, int[] statistics) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + statistics[0]);
            writer.newLine();
            writer.write("buy," + statistics[1]);
            writer.newLine();
            writer.write("result," + statistics[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't open the file " + toFileName + " ", e);
        }
    }
}
