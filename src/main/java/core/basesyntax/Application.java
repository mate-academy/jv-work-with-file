package core.basesyntax;

public class Application {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "result.csv");
    }
}
