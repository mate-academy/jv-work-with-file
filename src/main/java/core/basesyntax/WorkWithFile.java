package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String operation = parts[0];
                int amount = Integer.parseInt(parts[1].trim());
                if (operation.equals("supply")) {
                    supplySum += amount;
                } else if (operation.equals("buy")) {
                    buySum += amount;
                }
            }
            int result = supplySum - buySum;

            writeToFile(writer, "supply", supplySum);
            writeToFile(writer, "buy", buySum);
            writeToFile(writer, "result", result);

        } catch (IOException e) {
            throw new RuntimeException("Can't read/write data...", e);
        }

    }

    private void writeToFile(BufferedWriter writer, String operation, int amount)
            throws IOException {
        writer.write(operation + "," + amount);
        writer.newLine();
    }
}
