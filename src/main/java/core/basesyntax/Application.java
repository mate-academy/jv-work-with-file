package core.basesyntax;

public class Application {
    public static void main(String[] args) {
        WorkWithFile workFile = new WorkWithFile();
        String fromFile = "banana.csv";
        String toFile = "calculateBanana.csv";
        workFile.getStatistic(fromFile, toFile);
    }
}
