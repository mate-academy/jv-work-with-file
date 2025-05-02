package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(
                "src/main/java/core/basesyntax/input.csv",
                "src/main/java/core/basesyntax/output.csv"
        );
    }
}
