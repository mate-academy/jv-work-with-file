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

        try {
            String reportContent = Files.readString(Paths.get(outputFileName));
            System.out.println("Report content:\n" + reportContent);
        } catch (IOException e) {
            System.err.println("Failed to read report file: " + e.getMessage());
        }
    }
}
