package core.basesyntax;

import java.io.File;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        TaskFinalizer taskFinalizer = new TaskFinalizer();
        Writer writer = new Writer();
        String[] data = new DataExtractor().extract(file);
        String finalString = taskFinalizer.finalString(data);
        writer.write(finalString, toFileName);
    }
}
