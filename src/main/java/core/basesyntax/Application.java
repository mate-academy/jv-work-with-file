package core.basesyntax;

public class Application {
    private static final String BANANA = "banana.csv";
    private static final String APPLE = "apple.csv";
    private static final String GRAPE = "grape.csv";
    private static final String ORANGE = "orange.csv";
    private static final String REPORT = "report.csv";

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(BANANA, REPORT);
    }
}
