package core.basesyntax;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String [] files = new String[] {
                "apple.csv",
                "banana.csv",
                "grape.csv",
                "orange.csv"
        };
        String [] resultFiles = new String[]{
                "apple_result.txt",
                "banana_result.txt",
                "grape_result.txt",
                "orange_result.txt"
        };

        WorkWithFile workWithFile = new WorkWithFile();
        for (int i = 0; i < files.length; i++) {
            workWithFile.getStatistic(files[i], resultFiles[i]);
        }
    }
}
