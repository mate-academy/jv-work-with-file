package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY_TYPE = 0;
    private static final int BUY_TYPE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int operationType = "supply".equals(parts[0]) ? SUPPLY_TYPE : BUY_TYPE;
                int amount = Integer.parseInt(parts[1]);
                if (operationType == SUPPLY_TYPE) {
                    supply += amount;
                } else if (operationType == BUY_TYPE) {
                    buy += amount;
                }
            }
            int result = supply - buy;
            writeResult(toFileName, supply, buy, result);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file", e);
        }
    }

    private void writeResult(String fileName, int supply, int buy, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("supply," + supply + "\n");
            writer.write("buy," + buy + "\n");
            writer.write("result," + result + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file", e);
        }
    }
}
