package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SENTENCE_SPLITTER = " ";
    private static final String SUBSTRING_REGEX = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] reportFromFile = getReportFromFile(fromFileName);
        writeToFile(reportFromFile, toFileName);

    }

    private String[] getReportFromFile(String fromFileName) {
        int supplyPrice = 0;
        int buyPrice = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains("supply")) {
                    supplyPrice += Integer.parseInt(line.substring(line.indexOf(',') + 1));
                } else {
                    buyPrice += Integer.parseInt(line.substring(line.indexOf(',') + 1));
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        String report = "supply," + supplyPrice + SENTENCE_SPLITTER
                + "buy," + buyPrice + SENTENCE_SPLITTER
                + "result," + (supplyPrice - buyPrice);
        return report.split(SENTENCE_SPLITTER);
    }

    private void writeToFile(String[] report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            for (String s : report) {
                bufferedWriter.write(s + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
