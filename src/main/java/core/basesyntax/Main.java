package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        WorkWithFile fruitCalculator = new WorkWithFile();
        fruitCalculator.getStatistic("apple.csv", "output.txt");
        fruitCalculator.getStatistic("banana.csv", "output.txt");
        fruitCalculator.getStatistic("grape.csv", "output.txt");
        fruitCalculator.getStatistic("orange.csv", "output.txt");
    }
}
