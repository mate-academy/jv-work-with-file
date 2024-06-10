package core.basesyntax;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        String outputDirectory = "output";

        File directory = new File(outputDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String[] inputFiles = {"apple.csv", "banana.csv", "grape.csv", "orange.csv"};
        for (String inputFile : inputFiles) {
            String outputFile = outputDirectory + "/" + inputFile.replace(".csv", "_output.csv");
            workWithFile.getStatistic(inputFile, outputFile);
        }
    }
}
