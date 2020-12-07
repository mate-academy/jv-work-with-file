package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_FIELD = "supply";
    private static final String BUY_FIELD = "buy";
    private static final String SPLITTER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readDataFromFile(fromFileName);
        writeDataToFile(toFileName, data[0], data[1]);
    }

    private int[] readDataFromFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int[] data = {0, 0};
            String row = reader.readLine();
            while (row != null) {
                if (row.split(SPLITTER)[0].equals(SUPPLY_FIELD)) {
                    data[0] += Integer.parseInt(row.split(SPLITTER)[1]);
                } else if (row.split(SPLITTER)[0].equals(BUY_FIELD)) {
                    data[1] += Integer.parseInt(row.split(SPLITTER)[1]);
                }
                row = reader.readLine();
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + file, e);
        }
    }

    private void writeDataToFile(String file, int supply, int sales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(sales).append(System.lineSeparator())
                    .append("result,").append(supply - sales);
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + file, e);
        }
    }
}
