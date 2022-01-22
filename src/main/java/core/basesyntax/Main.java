package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "appleResult.csv");
        workWithFile.getStatistic("banana.csv", "bananaResult.csv");
        workWithFile.getStatistic("grape.csv", "grapeResult.csv");
        workWithFile.getStatistic("orange.csv", "orangeResult.csv");
    }
}
