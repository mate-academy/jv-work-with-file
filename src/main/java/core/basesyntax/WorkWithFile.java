package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        // Getting supply & buy values from file
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String value;
            while ((value = br.readLine()) != null) {
                String[] values = value.split(",");
                if (values[0].equals(SUPPLY_OPERATION)) {
                    supply += Integer.parseInt(values[1]);
                } else if (values[0].equals(BUY_OPERATION)) {
                    buy += Integer.parseInt(values[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + fromFileName, e);
        }
        // Writing final stats
        writeStatistic(toFileName, supply, buy);
    }

    private void writeStatistic(String toFileName, int supply, int buy) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write("supply," + supply);
            bw.newLine();
            bw.write("buy," + buy);
            bw.newLine();
            bw.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write statistics to file" + toFileName, e);
        }
    }
}
