package core.basesyntax;

import java.io.File;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        TaskFinalizer taskFinalizer = new TaskFinalizer();
        Writer writer = new Writer();
        writer.write(taskFinalizer.finalString(new DataExtractor().extract(file)), toFileName);
    }
}
