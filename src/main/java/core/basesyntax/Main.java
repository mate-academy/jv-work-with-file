package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] inputFiles = {"apple.csv", "banana.csv", "grape.csv", "orange.csv"};
        String[] outputFiles = {"newFile1.txt", "newFile2.txt", "newFile3.txt", "newFile4.txt"};
        WorkWithFile workWithFile = new WorkWithFile();

        for (int i = 0; i < inputFiles.length; i++) {
            String fromFile = inputFiles[i];
            String newFile = outputFiles[i];
            workWithFile.getStatistic(fromFile, newFile);
        }
    }
}
