package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] report = createReport(fromFileName);
        writeReport(toFileName, report);
    }

    private int[] createReport(String fromFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fromFileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                String[] parts = line.split(",");
                if (!line.isEmpty() && parts.length == 2) {
                    String op = parts[0].trim();
                    String val = parts[1].trim();

                    try {
                        int value = Integer.parseInt(val);

                        if (SUPPLY.equalsIgnoreCase(op)) {
                            supply += value;
                        } else if (BUY.equalsIgnoreCase(op)) {
                            buy += value;
                        }
                    } catch (NumberFormatException e) {
                        // skip invalid numeric value
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: " + fromFileName, e);
        }
        return new int[]{supply, buy};
    }

    private void writeReport(String toFileName, int[] report) {
        int supply = report[0];
        int buy = report[1];
        int result = supply - buy;

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(toFileName),
                        StandardCharsets.UTF_8))) {
            writer.write(SUPPLY + "," + supply);
            writer.newLine();
            writer.write(BUY + "," + buy);
            writer.newLine();
            writer.write(RESULT + "," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: " + toFileName, e);
        }
    }
}
