package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_KEY = "supply";
    private static final String BUY_KEY = "buy";
    private static final String RESULT_KEY = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = calculateTotals(fromFileName);
        String report = buildReport(totals[0], totals[1]);
        writeReport(toFileName, report);
    }

    private int[] calculateTotals(String fromFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int value = Integer.parseInt(parts[1]);
                if (SUPPLY_KEY.equals(parts[0])) {
                    supply += value;
                } else if (BUY_KEY.equals(parts[0])) {
                    buy += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return new int[]{supply, buy};
    }

    private String buildReport(int supply, int buy) {
        int result = supply - buy;
        String ls = System.lineSeparator();
        return SUPPLY_KEY + "," + supply + ls
                + BUY_KEY + "," + buy + ls
                + RESULT_KEY + "," + result;
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
