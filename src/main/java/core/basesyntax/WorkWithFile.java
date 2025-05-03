package core.basesyntax;

import java.io.File;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        Writer writer = new Writer();
        String[] data = new DataReader().readFile(file);
        String count = new ReportCreator().getReport(data);
        writer.writeDataToFile(count, toFileName);
    }
}
