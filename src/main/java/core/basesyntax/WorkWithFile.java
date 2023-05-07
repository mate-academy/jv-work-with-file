package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int FiRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATE = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int [] data = readFile(fromFileName);
        String report = createReport(data);
        writeReport(toFileName,report);
    }

    private int [] readFile(String fromFileName) {
        int [] values = new int[2];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String [] separate = value.split(SEPARATE);
                if (separate[FiRST_POSITION].equals(SUPPLY)) {
                    values[FiRST_POSITION] += Integer.parseInt(separate[SECOND_POSITION]);
                }
                if (separate[FiRST_POSITION].equals(BUY)) {
                    values[SECOND_POSITION] += Integer.parseInt(separate[SECOND_POSITION]);
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file" + fromFileName, e);
        }
        return values;
    }

    private String createReport(int[] data) {
        int result = data[0] - data[1];
        return "supply," + data[0] + System.lineSeparator()
                + "buy," + data[1] + System.lineSeparator()
                + "result," + result;
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
