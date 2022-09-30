package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class WorkWithFile {
    private String readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;

        // Read from file and store the info in supplyTotal and buyTotal
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String [] line = value.split(",");
                if (line[0].equals("supply")) {
                    supplyTotal += Integer.parseInt(line[1]);
                } else if (line[0].equals("buy")) {
                    buyTotal += Integer.parseInt(line[1]);
                }
                value = reader.readLine();
            }
            stringBuilder.append("supply," + supplyTotal + System.lineSeparator()
                    + "buy," + buyTotal + System.lineSeparator()
                    + "result," + (supplyTotal - buyTotal));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        return new String(stringBuilder);
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String readData = readFromFile(fromFileName);

        // Writing data to a file
        try (BufferedWriter ButterWriter = new BufferedWriter(new FileWriter(toFileName))) {
            ButterWriter.write(readData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a new file", e);
        }
    }
}
