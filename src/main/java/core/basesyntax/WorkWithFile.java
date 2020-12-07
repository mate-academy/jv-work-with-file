package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMA = ",";
    public static final int ZERO = 0;
    int supply = 0;
    int buy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] info = value.split(COMA);
                if (info[ZERO].equals("supply")) {
                    supply += Integer.parseInt(info[1]);
                }
                if (info[ZERO].equals("buy")) {
                    buy += Integer.parseInt(info[1]);
                }
                value = reader.readLine();
            }
            writeToFile(toFileName, supply, buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result," + (supply - buy)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write write data to file", e);
        }
    }
}
