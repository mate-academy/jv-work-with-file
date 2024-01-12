package core.basesyntax;

public class Runner {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("banana.csv","test.txt");
    }
}
