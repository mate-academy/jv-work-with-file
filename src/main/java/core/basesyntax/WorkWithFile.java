package core.basesyntax;

import java.util.List;

public class WorkWithFile {
    private ReaderOurCsv reader = new ReaderOurCsv();
    private MakerStatistic makerStatistic = new MakerStatistic();
    private WriterToCsv writer = new WriterToCsv();

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> recordsFromReadingFile = reader.getArrayRecords(fromFileName);
        String[] recordsForWriting = makerStatistic.makeStatistic(recordsFromReadingFile);
        writer.writeRecords(toFileName, recordsForWriting);
    }
}
