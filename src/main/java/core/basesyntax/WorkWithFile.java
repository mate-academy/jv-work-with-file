package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        try {
            List<String> strings = Files.readAllLines(file.toPath());
            System.out.println(strings);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        File file1 = new File(toFileName);
        try {
            Files.write(file.toPath(), toFileName.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

}

