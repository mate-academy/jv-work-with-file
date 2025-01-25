package core.basesyntax;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WorkWithFile workWithFile = new WorkWithFile();

        String fromFileName = "banana.csv";
        String toFileName = "reports.csv";

        File reportFile = new File(toFileName);
        if (!reportFile.exists()) {
            try {
                if (reportFile.createNewFile()) {
                    System.out.println("New report file created.");
                }
            } catch (IOException e) {
                System.out.println("Error creating the report file: " + e.getMessage());
                return;
            }
        }
        workWithFile.getStatistic(fromFileName, toFileName);
    }
}
