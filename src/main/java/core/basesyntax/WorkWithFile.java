package core.basesyntax;

import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        Reader readingFile = new Reader();
        ArrGenerator arr = new ArrGenerator();
        ReportCreator report = new ReportCreator();
        FileCreator file = new FileCreator();
        String result;
        try {
            result = readingFile.reader(fromFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + e);
        }
        file.fileCreator(toFileName, report.reportCreator(arr.arrGenerator(result)));
    }
}
