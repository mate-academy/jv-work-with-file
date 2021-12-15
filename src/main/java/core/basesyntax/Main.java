package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "resultApple.csv");
        workWithFile.getStatistic("banana.csv", "resultBanana.csv");
        workWithFile.getStatistic("grape.csv", "resultGrape.csv");
        workWithFile.getStatistic("orange.csv", "resultOrange.csv");
    }
}
