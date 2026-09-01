package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, readFromFile(fromFileName));
    }

    public void writeToFile(String toFileName, Summary summary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(summary.toCsv());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }

    public Summary readFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            String[] rows;
            int supplySum = 0;
            int buySum = 0;

            while ((line = reader.readLine()) != null) {
                rows = line.split(",");
                switch (rows[INDEX_TYPE]) {
                    case "supply" -> supplySum += Integer.parseInt(rows[INDEX_AMOUNT]);
                    case "buy" -> buySum += Integer.parseInt(rows[INDEX_AMOUNT]);
                    default -> {
                        throw new IllegalArgumentException("Unknown type: " + rows[INDEX_TYPE]);
                    }
                }
            }

            return new Summary(supplySum, buySum);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
    }
}
