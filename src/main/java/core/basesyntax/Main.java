package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final String FROM_FILE = "banana.csv";
    private static final String RESULT_FILE = "test1.csv";

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        try {
            Files.deleteIfExists(Path.of(RESULT_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly clear result files after test ", e);
        }
        workWithFile.getStatistic(FROM_FILE, RESULT_FILE);
    }
}
