package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "apple");
        workWithFile.getStatistic("banana.csv", "banana");
        workWithFile.getStatistic("grape.csv", "grape");
        workWithFile.getStatistic("orange.csv", "orange");
    }
}
