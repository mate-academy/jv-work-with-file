package core.basesyntax;

public class WorkWithFileApp {
    public static void main(String[] args) {
        WorkWithFile withFile = new WorkWithFile();
        withFile.getStatistic("grape.csv", "report.csv");
    }
}
