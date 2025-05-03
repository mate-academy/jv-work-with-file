package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        String fromFileName = "apple.csv";
        String toFileName = "newFile.csv";

        workWithFile.getStatistic(fromFileName, toFileName);

        System.out.println("Statistics processed and saved to file " + toFileName);
    }
}
