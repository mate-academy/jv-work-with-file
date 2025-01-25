package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        final String FROM_FILE_NAME = "banana.csv";
        final String TO_FILE_NAME = "result.csv";

        File reportFile = new File(TO_FILE_NAME);
        if (!reportFile.exists()) {
            try {
                if (reportFile.createNewFile()) {
                    System.out.println("New report file created.");
                }
            } catch (IOException e) {
                throw new RuntimeException("Error creating the report file: " + e.getMessage(), e);
            }
        }
        workWithFile.getStatistic(FROM_FILE_NAME, TO_FILE_NAME);
    }
}
