package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            File fileFrom = new File("banana.csv");
            Path path1 = fileFrom.toPath();
            String fileNameFrom = String.valueOf(path1);

            File fileTo = new File("report.csv");
            Path path2 = fileTo.toPath();
            String fileNameTo = String.valueOf(path2);
            fileTo.createNewFile();

            WorkWithFile.getStatistic(fileNameFrom, fileNameTo);

        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
    }
}
