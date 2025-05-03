package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "appleV2.csv");
        workWithFile.getStatistic("banana.csv", "bananaV2.csv");
    }
}
