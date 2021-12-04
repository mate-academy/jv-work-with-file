package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile work = new WorkWithFile();
        work.getStatistic("apple.csv","appleResult.csv");
        work.getStatistic("banana.csv","bananaResult.csv");
        work.getStatistic("grape.csv","grapeResult.csv");
        work.getStatistic("orange.csv","orangeResult.csv");
    }
}
