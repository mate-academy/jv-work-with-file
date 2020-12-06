package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readDataFromFile(fromFileName);
        writeDataToFile(toFileName, data[0], data[1]);
    }

    private int[] readDataFromFile(String file) {
        try {
            BufferedReader readSupply = new BufferedReader(new FileReader(file));
            int[] data = new int[2];
            data[0] = getSupplyCount(readSupply);
            BufferedReader readSales = new BufferedReader(new FileReader(file));
            data[1] = getSellCount(readSales);
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
    }

    private void writeDataToFile(String file, int supply, int sales) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            StringBuilder builder = new StringBuilder();
            builder.append("supply,").append(supply).append(System.lineSeparator());
            builder.append("buy,").append(sales).append(System.lineSeparator());
            builder.append("result,").append(supply - sales);
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private int getSupplyCount(BufferedReader reader) throws IOException {
        int supply = 0;
        String line = reader.readLine();
        String[] row;
        while (line != null) {
            row = line.split(",");
            if (row[0].equals("supply")) {
                supply += Integer.parseInt(row[1]);
            }
            line = reader.readLine();
        }
        reader.close();
        return supply;
    }

    private int getSellCount(BufferedReader reader) throws IOException {
        int sales = 0;
        String line = reader.readLine();
        String[] row;
        while (line != null) {
            row = line.split(",");
            if (row[0].equals("buy")) {
                sales += Integer.parseInt(row[1]);
            }
            line = reader.readLine();
        }
        reader.close();
        return sales;
    }
}
