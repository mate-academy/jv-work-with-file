package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String value = reader.readLine();
            while (value != null) {
                if (value.split(",")[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(value.split(",")[1]);
                } else {
                    buy += Integer.parseInt(value.split(",")[1]);
                }
                value = reader.readLine();
            }
            writeStatistic(writer, buy, supply);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file ", e);
        }
    }

    public void writeStatistic(BufferedWriter writer, int buy, int supply) throws IOException {
        String str = "supply," + supply + System.lineSeparator() + "buy," + buy
                + System.lineSeparator() + "result,"
                + (supply - buy);
        writer.write(str);
    }
}
