package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        System.out.println(workWithFile.readFromFile("apple.csv"));
    }
}
