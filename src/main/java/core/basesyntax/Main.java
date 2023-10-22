package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        File file = new File("appleResult.csv");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fromFileName = "apple.csv";
        String toFileName = file.getPath();
        workWithFile.getStatistic(fromFileName,toFileName);

    }

}
