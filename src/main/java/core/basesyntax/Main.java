package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "input.csv";
        String outputFileName = "output.csv";

        WorkWithFile workWithFile = new WorkWithFile();
        workWithFile.getStatistic(inputFileName, outputFileName);

        System.out.println("Report generated successfully in " + outputFileName);
    }
}
