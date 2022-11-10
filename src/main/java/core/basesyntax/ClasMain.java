package core.basesyntax;

public class ClasMain {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("banana.csv","banana_report.csv");
    }
}
