package core.basesyntax.io;

import core.basesyntax.io.exception.CsvException;
import core.basesyntax.model.Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String REPORT_FORMAT = "supply%s%d%sbuy%s%d%sresult%s%d";

    public void writeReportToCsv(Report report, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String csvString = reportToCsv(report);
            writer.write(csvString);
            writer.flush();
        } catch (IOException e) {
            throw new CsvException("Cannot access file with name = [" + fileName + "]");
        }
    }

    private String reportToCsv(Report report) {
        return String.format(REPORT_FORMAT,
                DELIMITER,
                report.getSupply(),
                NEW_LINE,
                DELIMITER,
                report.getBuy(),
                NEW_LINE,
                DELIMITER,
                report.getResult());
    }
}
