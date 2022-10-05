package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supplyTotal = 0;
    private int buyTotal = 0;

    private String readData() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + (supplyTotal - buyTotal));
        return new String(stringBuilder);
    }

    private void readFromFile(String fromFileName) {
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
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter ButterWriter = new BufferedWriter(new FileWriter(toFileName))) {
            ButterWriter.write(readData());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a new file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        writeToFile(toFileName);
    }
}
