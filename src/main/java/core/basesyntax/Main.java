package core.basesyntax;

import java.io.File;

public class Main {
    private static final String APPLE_FILE = "apple.csv";
    private static final String BANANA_FILE = "banana.csv";
    private static final String GRAPE_FILE = "grape.csv";
    private static final String ORANGE_FILE = "orange.csv";

    private static final String APPLE_RESULT_FILE = "appleResult.csv";
    private static final String GRAPE_RESULT_FILE = "grapeResult.csv";
    private static final String ORANGE_RESULT_FILE = "orangeResult.csv";
    private static final String BANANA_RESULT_FILE = "bananaResult.csv";

    public static void main(String[] args) {
        File file = new File(APPLE_FILE);
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(APPLE_FILE, APPLE_RESULT_FILE);
    }
}
