package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        File file = new File("testFile.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        workWithFile.getStatistic("apple.csv","testFile.txt");
    }
}
