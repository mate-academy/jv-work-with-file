package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeReportIntoFile(toFileName, createReport(fromFileName));
    }

    public String createReport(String fromFileName) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName)) {

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReportIntoFile(String toFileName, String report) {

    }
}
