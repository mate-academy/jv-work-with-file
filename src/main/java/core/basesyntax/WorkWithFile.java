package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_TYPE = 0;
    private static final int BUY_TYPE = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private int supply = 0;
    private int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        supply = 0;
        buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
            int result = supply - buy;
            writeResult(toFileName, supply, buy, result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(",");
        int operationType = SUPPLY.equals(parts[0]) ? SUPPLY_TYPE : BUY_TYPE;
        int amount = Integer.parseInt(parts[1]);
        if (operationType == SUPPLY_TYPE) {
            supply += amount;
        } else if (operationType == BUY_TYPE) {
            buy += amount;
        }
    }

    private void writeResult(String fileName, int supply, int buy, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(SUPPLY + "," + supply + "\n");
            writer.write(BUY + "," + buy + "\n");
            writer.write(RESULT + "," + result + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
