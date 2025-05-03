package core.basesyntax;

public class Main {

    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv", "apple_report.csv");
        System.out.println(System.lineSeparator());
        workWithFile.getStatistic("banana.csv", "banana_report.csv");
        System.out.println(System.lineSeparator());
        workWithFile.getStatistic("grape.csv", "grape_report.csv");
        System.out.println(System.lineSeparator());
        workWithFile.getStatistic("orange.csv", "orange_report.csv");
    }
}
