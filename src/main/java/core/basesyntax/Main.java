package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile file = new WorkWithFile();
        file.getStatistic("orange.csv", "test.txt");
    }
}
