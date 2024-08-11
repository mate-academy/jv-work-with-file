package core.basesyntax.io;

import core.basesyntax.io.exception.CsvException;
import core.basesyntax.model.Report;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private static final String REPORT_FORMAT = "supply,%d%nbuy,%d%nresult,%d";

    public void writeReportToCsv(Report report, String fileName) {
        String csvString = reportToCsv(report);
        writeStringToFile(fileName, csvString);
    }

    private void writeStringToFile(String fileName, String csvString) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(csvString);
            writer.flush();
        } catch (IOException e) {
            throw new CsvException("Cannot access file = [" + fileName + "]", e);
        }
    }

    private String reportToCsv(Report report) {
        return String.format(REPORT_FORMAT,
                report.getSupply(),
                report.getBuy(),
                report.getResult());
    }
}
