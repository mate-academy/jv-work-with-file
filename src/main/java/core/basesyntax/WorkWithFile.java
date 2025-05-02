package core.basesyntax;

import core.basesyntax.io.CsvReader;
import core.basesyntax.io.CsvWriter;
import core.basesyntax.model.Operation;
import core.basesyntax.model.Report;
import java.util.List;

public class WorkWithFile {
    private final CsvReader reader = new CsvReader();
    private final CsvWriter writer = new CsvWriter();

    public void getStatistic(String fromFileName, String toFileName) {
        List<Operation> operations = reader.readOperations(fromFileName);
        Report report = new Report();
        report.ofOperations(operations);
        writer.writeReportToCsv(report, toFileName);
    }
}
