package core.basesyntax;

public class MainTest {
    public static void main(String[] args) {
        WorkWithFile test = new WorkWithFile();
        test.getStatistic("apple.csv", "output.csv");
    }
}
