package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        try {
            List<String> strings = Files.readAllLines(fromFile.toPath());
            String[] data = new String[strings.size()];
            strings.toArray(data);
            ReportCreating reportCreating = new ReportCreating();
            String[] report = reportCreating.createReport(data);
            for (String stringReport : report) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(
                        new FileWriter((toFile), true))) {
                    bufferedWriter.write(stringReport);
                    bufferedWriter.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
    }
}
