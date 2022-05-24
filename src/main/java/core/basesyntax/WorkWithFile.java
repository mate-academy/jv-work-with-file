package core.basesyntax;

import java.io.File;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        Writer writer = new Writer();
        String[] data = new DataExtractor().extract(file);
        String count = new AmountCounter().count(data);
        writer.write(count, toFileName);
    }
}
