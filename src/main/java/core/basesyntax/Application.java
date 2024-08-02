package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        File reportFile = new File("report.csv");
        try {
            reportFile.createNewFile();
            workWithFile.getStatistic("orange.csv", "report.csv");
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
