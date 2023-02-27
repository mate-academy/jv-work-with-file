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
            String readFile = WorkWithFile.readFromFile(fileNameFrom);

            File fileTo = new File("report.csv");
            Path path2 = fileTo.toPath();
            String fileNameTo = String.valueOf(path2);
            fileTo.createNewFile();

            String str = WorkWithFile.getStatistic(readFile, fileNameTo);

            WorkWithFile.writeToFile(fileNameTo, str);

        } catch (IOException e) {
            throw new RuntimeException("Can`t create file", e);
        }
    }
}
