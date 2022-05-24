package core.basesyntax;

import java.io.File;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        Writer writer = new Writer();
        String[] data = new DataReader().extract(file);
        String count = new GetCalculation().count(data);
        writer.write(count, toFileName);
    }
}
