package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Writer {
    public void createCsvFile(String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file " + toFileName, e);
        }
    }

    public void writeCsvFileWithData(Path pathFile,
                                     int buyCount,
                                     int supplyCount,
                                     int total) {
        try {
            Files.write(pathFile, Arrays.asList("supply," + supplyCount,
                                                "buy," + buyCount,
                                                "result," + total));
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + pathFile, e);
        }
    }
}
