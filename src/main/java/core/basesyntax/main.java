package core.basesyntax;

public class main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        workWithFile.getStatistic("apple.csv", "appleResult.csv");
    }
}
