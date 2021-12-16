package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> rawData = readRawData(fromFileName);
        int[] reportData = calculateReport(rawData);
        writeReportData(toFileName, reportData);
    }

    private List<String> readRawData(String fromFileName) {
        List<String> rawData;
        try {
            rawData = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return rawData;
    }

    private int[] calculateReport(List<String> rawData) {
        int[] reportData = new int[] {0, 0, 0};
        String[] splitRawDataEntry;
        for (String string: rawData) {
            splitRawDataEntry = string.split(CSV_SEPARATOR);
            if (splitRawDataEntry[0].equals("supply")) {
                reportData[0] += Integer.parseInt(splitRawDataEntry[1]);
            }
            if (splitRawDataEntry[0].equals("buy")) {
                reportData[1] += Integer.parseInt(splitRawDataEntry[1]);
            }
        }
        reportData[2] = reportData[0] - reportData[1];
        return reportData;
    }

    private void writeReportData(String toFileName, int[] reportData) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply" + CSV_SEPARATOR + reportData[0] + System.lineSeparator()
                    + "buy" + CSV_SEPARATOR + reportData[1] + System.lineSeparator()
                    + "result" + CSV_SEPARATOR + reportData[2]);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
