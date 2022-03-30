package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "appleResult.scv");
        workWithFile.getStatistic("banana.csv", "bananaResult.scv");
        workWithFile.getStatistic("grape.csv", "grapeResult.scv");
        workWithFile.getStatistic("orange.csv", "orangeResult.scv");
    }
}
