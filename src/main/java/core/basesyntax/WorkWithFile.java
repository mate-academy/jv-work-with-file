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
        int[] calc = calculateValue(fromFileName);
        String report = getReport(calc);
        writeToFile(report, toFileName);
    }

    private int[] calculateValue(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] lineArray = line.split(",");
                if (lineArray[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(lineArray[1]);
                } else if (lineArray[0].equals(BUY)) {
                    buy += Integer.parseInt(lineArray[1]);
                }
                line = reader.readLine();
            }
            return new int[]{supply, buy};

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private String getReport(int[] calculated) {
        int supply = calculated[0];
        int buy = calculated[1];
        String report = SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + RESULT + "," + (supply - buy) + System.lineSeparator();
        return report;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

}
