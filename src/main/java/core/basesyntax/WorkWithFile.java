package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_VALUE = 1;
    private static final String CVS_SEPARATOR = ",";

public void getStatistic(String fromFileName, String toFileName) {
        writeReport(toFileName, createAndGetReportFromFile(fromFileName));
    }

    private String createAndGetReportFromFile(String fileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String readable;
            while ((readable = bufferedReader.readLine()) != null) {
                if (readable.contains("supply")) {
                    supply += Integer.parseInt(readable.split(CVS_SEPARATOR)[INDEX_OF_VALUE]);
                }
                if (readable.contains("buy")) {
                    buy += Integer.parseInt(readable.split(CVS_SEPARATOR)[INDEX_OF_VALUE]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return new StringBuilder().append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy).append(System.lineSeparator()).toString();
    }

    private void writeReport(String fileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + fileName, e);
        }
    }
}
