package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int buySum = 0;
    private int supplySum = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        calculateReport();
        writeToFile(toFileName);
        cleanUp();
    }

    private void cleanUp() {
        buySum = 0;
        supplySum = 0;
    }

    public void readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(type)) {
                    supplySum += amount;
                } else if ("buy".equals(type)) {
                    buySum += amount;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file ", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
    }

    public int calculateReport() {
        return supplySum - buySum;
    }

    public void writeToFile(String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(supplySum).append(System.lineSeparator())
                    .append("buy,").append(buySum).append(System.lineSeparator())
                    .append("result,").append(calculateReport());
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write file ", e);
        }
    }
}
