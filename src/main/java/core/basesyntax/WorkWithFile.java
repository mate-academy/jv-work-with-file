package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int[] calc = calculateValue(fromFileName);
        String report = getReport(calc);
        writeToFile(report, toFileName);
    }

    public int[] calculateValue(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] lineArray = line.split(",");
                if (lineArray[0].equals("supply")) {
                    supply += Integer.parseInt(lineArray[1]);
                } else if (lineArray[0].equals("buy")) {
                    buy += Integer.parseInt(lineArray[1]);
                }
                line = reader.readLine();
            }
            return new int[]{supply, buy};

        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    public String getReport(int[] calculated) {
        int supply = calculated[0];
        int buy = calculated[1];
        String report = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy) + System.lineSeparator();
        return report;
    }

    public void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }

}
