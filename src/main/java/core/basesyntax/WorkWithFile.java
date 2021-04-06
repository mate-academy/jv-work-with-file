package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SPLITTER = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final int ZERO_POINT = 0;
    public static final int FIRTS_POINT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int suply = 0;
            int buy = 0;
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] statistic = line.split(SPLITTER);
                if (statistic[ZERO_POINT].equals(SUPPLY)) {
                    suply += Integer.parseInt(statistic[FIRTS_POINT]);
                }
                if (statistic[ZERO_POINT].equals(BUY)) {
                    buy += Integer.parseInt(statistic[FIRTS_POINT]);
                }
                line = bufferedReader.readLine();
            }
            writeToFile(toFileName, createReport(suply, buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName,e);
        }
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + toFileName, e);
        }
    }

    private String createReport(int supply, int buy) {
        String report = SUPPLY + "," + supply + System.lineSeparator()
                + BUY + "," + buy + System.lineSeparator()
                + "result" + "," + (supply - buy);
        return report;
    }
}
