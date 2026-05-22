package core.basesyntax;

public class Application {
    public static void main(String[] args) {
        WorkWithFile fileWork = new WorkWithFile();

        fileWork.getStatistic("orange.csv", "output.csv");
    }
}
