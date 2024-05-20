package core.basesyntax;

public class MainApp {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "result.txt");
    }
}
