package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile wf = new WorkWithFile();
        wf.getStatistic("orange.csv", "report.csv");
    }
}
