package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] inputFiles = {"apple.csv", "banana.csv", "grape.csv", "orange.csv"};
        String[] outputFiles = {"newFile1.txt", "newFile2.txt", "newFile3.txt", "newFile4.txt"};
        WorkWithFile workWithFile = new WorkWithFile();

        for (int i = 0; i < inputFiles.length; i++) {
            String fromFileName = inputFiles[i];
            String newFile = outputFiles[i];

            String result = workWithFile.getStatistic(fromFileName, newFile);
            System.out.println(result);
        }
    }
}
