package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WorkWithFile workWithFile = new WorkWithFile();
        File fileTo = new File("сюда.csv");
        File fileFrom = new File("apple.csv");
        if (fileTo.exists()) {
            fileFrom.delete();
        } else {
            fileFrom.createNewFile();
        }

        workWithFile.getStatistic("apple.csv","сюда.csv");
    }
}
