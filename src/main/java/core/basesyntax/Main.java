package core.basesyntax;

import java.io.File;

public class Main {
    private static final String FROM_PATH = "."
            + File.separator + "files"
            + File.separator + "from.csv";
    private static final String TO_PATH = "."
            + File.separator + "files"
            + File.separator + "to.csv";

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(FROM_PATH, TO_PATH);
    }
}
