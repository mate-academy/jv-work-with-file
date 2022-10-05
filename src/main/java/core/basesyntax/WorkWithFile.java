package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    ReaderOurCSV reader = new ReaderOurCSV();
    MakerStatistic makerStatistic = new MakerStatistic();

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File(toFileName), true))) {
            for (String record : makerStatistic
                    .makeStatistic(reader.getArrayRecords(fromFileName))) {
                bufferedWriter.write(record);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write info to file", e);
        }
    }
}
