package core.basesyntax;

import core.basesyntax.read.and.write.Reader;
import core.basesyntax.read.and.write.Writer;
import core.basesyntax.service.OperationService;
import core.basesyntax.service.ReportService;

public class WorkWithFile {
    private final Reader reader = new Reader();
    private final Writer writer = new Writer();
    private final OperationService operationService = new OperationService();
    private final ReportService reportService = new ReportService();

    public void getStatistic(String fromFileName, String toFileName) {
        String[] infoFromFile = reader.read(fromFileName);
        int[] salesVolume = operationService.getResult(infoFromFile);
        String report = reportService.generateReport(salesVolume);
        writer.write(toFileName, report);
    }
}
