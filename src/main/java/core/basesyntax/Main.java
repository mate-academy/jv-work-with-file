package core.basesyntax;

import static core.basesyntax.WorkWithFile.getStatistic;

public class Main {
    public static void main(String[] args) {
        getStatistic("apple.csv", "apple_report.csv");
        getStatistic("banana.csv", "banana_report.csv");
        getStatistic("grape.csv", "grape_report.csv");
        getStatistic("orange.csv", "orange_report.csv");
    }

}
