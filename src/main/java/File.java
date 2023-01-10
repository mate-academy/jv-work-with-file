import core.basesyntax.WorkWithFile;

public class File {
    public static void file(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic("apple.csv","result.csv");
    }
}
