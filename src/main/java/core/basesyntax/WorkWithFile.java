package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile workWithFile = new WorkWithFile();
        int[] report = workWithFile.readFromFile(fromFileName);
        workWithFile.writeToFile(toFileName, report);

    }

    private int[] readFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        int[] report = {0, 0};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String value = bufferedReader.readLine();
            int parseInt = 0;
            while (value != null) {
                parseInt = Integer.parseInt(value.split(",")[1]);
                if (value.startsWith("supply")) {
                    report[0] += parseInt;
                } else {
                    report[1] += parseInt;
                }
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file");
        }
        return report;
    }

    private void writeToFile(String toFileName, int[] report) {
        File toFile = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write("supply," + report[0]
                    + System.lineSeparator() + "buy," + report[1]
                    + System.lineSeparator() + "result,"
                    + (report[0] - report[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
