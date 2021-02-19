package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String fromFileName = "apple.csv";
        String toFileName = "result";
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(fromFileName, toFileName);
    }
}
