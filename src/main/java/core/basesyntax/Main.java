import core.basesyntax;

public class Main {
  public static void main(String[] args) {
    WorkWithFile workFile = new WorkWithFile();
    workFile.getStatistic("apple.csv", "summary_apple.csv");
    workFile.getStatistic("grape.csv", "summary_grape.csv");
    workFile.getStatistic("orange.csv", "summary_orange.csv");
    workFile.getStatistic("banana.csv", "summary_banana.csv");
  }
}
