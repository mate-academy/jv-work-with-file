package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "result.csv");
        workWithFile.getStatistic("banana.csv", "result.csv");
        workWithFile.getStatistic("grape.csv", "result.csv");
        workWithFile.getStatistic("orange.csv", "result.csv");
    }
}
