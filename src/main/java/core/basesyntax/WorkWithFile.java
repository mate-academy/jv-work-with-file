package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            String line;
            int supplySummary = 0;
            int buySummary = 0;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (SUPPLY.equals(data[0])) {
                    supplySummary += Integer.parseInt(data[1]);
                } else if (BUY.equals(data[0])) {
                    buySummary += Integer.parseInt(data[1]);
                }
            }
            writer.write(SUPPLY + "," + supplySummary);
            writer.newLine();
            writer.write(BUY + "," + buySummary);
            writer.newLine();
            writer.write(RESULT + "," + (supplySummary - buySummary));
        } catch (IOException e) {
            throw new RuntimeException("Can't read or write the File", e);
        }
    }
}
