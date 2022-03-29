package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile a = new WorkWithFile();
        a.getStatistic("apple.csv","apple_stat.csv");
        a.getStatistic("banana.csv","banana_stat.csv");
        a.getStatistic("grape.csv","grape_stat.csv");
        a.getStatistic("orange.csv","orange_stat.csv");
    }
}
