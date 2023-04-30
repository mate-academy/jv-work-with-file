package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_POSITION = 0;
    private static final int NUMBER_POSITION = 1;
    private static final String SEPARATE = ",";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] data = readFile(fromFileName);
        String report = createReport(data);
        writeReport(toFileName, report);
    }

    private int[] readFile(String fromFileName) {
        int[] values = new int[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] separate = line.split(SEPARATE);
                if (separate[NAME_POSITION].equals(SUPPLY)) {
                    values[NAME_POSITION] += Integer.parseInt(separate[NUMBER_POSITION]);
                } else {
                    values[NUMBER_POSITION] += Integer.parseInt(separate[NUMBER_POSITION]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return values;
    }

    private String createReport(int[] data) {
        int result = data[0] - data[1];
        return "supply," + data[0] + System.lineSeparator()
                + "buy," + data[1] + System.lineSeparator()
                + "result," + result + System.lineSeparator();
    }

    private void writeReport(String toFileName, String report) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
