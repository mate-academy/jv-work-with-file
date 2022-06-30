package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class MainWorkWithFile {
    public static void main(String[] args) {

        WorkWithFile workWithFile = new WorkWithFile();
        File reportFile = new File("report.csv");
        try {
            reportFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        workWithFile.getStatistic("apple.csv",reportFile.getName());

    }

}
