package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String [] files = new String[] {
                "apple.csv",
                "banana.csv",
                "grape.csv",
                "orange.csv"
        };
        String [] resultFiles = new String[]{
                "apple_result.csv",
                "banana_result.csv",
                "grape_result.csv",
                "orange_result.csv"
        };
        WorkWithFile workWithFile = new WorkWithFile();
        for (int i = 0; i < files.length; i++) {
            workWithFile.getStatistic(files[i], resultFiles[i]);
        }
    }
}
