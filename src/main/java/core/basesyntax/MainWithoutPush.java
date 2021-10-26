package core.basesyntax;

public class MainWithoutPush {
    public static void main(String[] args) {
        String fileNameFrom = "apple.csv";
        String fileNameTo = "apple_result.csv";
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(fileNameFrom, fileNameTo);
        fileNameFrom = "banana.csv";
        fileNameTo = "banana_result.csv";
        workWithFile.getStatistic(fileNameFrom, fileNameTo);
    }
}
